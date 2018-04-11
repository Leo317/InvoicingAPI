package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.security.model.RoleNames;
import com.example.security.service.TokenAuthenticationService;
import com.example.service.ShareService;

@RestController
@RequestMapping("/share")
public class ShareController {
	public static final String ACCESS_DENIED = "Access Denied.";
	@Autowired
	ShareService shareServ;
	
	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET, produces = {"application/json"})
	public Response findOne(@RequestParam(value = "id", 
	                        required = true, 
	                        defaultValue = "0") String id,
							HttpServletRequest request) {
		String[] token = request.getHeader(TokenAuthenticationService.HEADER_STRING).split(" ");
		if (token[1].compareTo(RoleNames.STORE.toString()) == 0) {

			// http://127.0.0.1:8080/share/getOrderList?id=1.aaa
			if (!NumberUtils.isDigits(id)) {
				return new AjaxResponse(Status.STATUS400, "Order id : " + id + " is not an integer!!!", null);
			} else {
				if (id.equals("0"))
					return new AjaxResponse(Status.SUCCESS, "", shareServ.findAll());
				else
					return new AjaxResponse(Status.SUCCESS, "", shareServ.findOne(Integer.parseInt(id)));
			}
		} else {
			return new AjaxResponse(Status.ERROR, ACCESS_DENIED, null);
		}
	}
	
	@RequestMapping(value = "/decode", method = RequestMethod.GET)
	public String getToken(HttpServletRequest request) {
		String[] user = request.getHeader(TokenAuthenticationService.HEADER_STRING).split(" ");
		String jwtToken = user[3];
		//Decode
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        //String base64EncodedSignature = split_string[2];
        //JWT Header
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        //JWT Body
        String body = new String(base64Url.decode(base64EncodedBody));
        StringBuilder response = new StringBuilder(user[0]);
        return response.append(",").append(user[1]).append(",").append(user[2])
        .append(header).append(",").append(body).toString();
	}
}

