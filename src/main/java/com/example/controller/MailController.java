package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Mailrecords;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.CommonTools;
import com.example.service.KeywordFinder;
import com.example.service.MailServiceImpl;
import com.example.view.TestDTO;

@RestController
public class MailController {
	@Autowired
	MailServiceImpl mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	private static final int MAX_EMAIL_SUPPORT = 3;
	
	@RequestMapping(name = "/totalEmail", method = RequestMethod.POST, produces = {"application/json"}, consumes="application/json")
	@ResponseBody
	public Response totalForEmailSending(@RequestBody TestDTO testDTO) {
		
		String id1 = testDTO.getId1(); 
		if(CommonTools.isInteger(id1) == false) {
	      return new AjaxResponse(Status.STATUS400, "This id1: " + id1 + " is not an integer.", null); 			
		}

		String id2 = testDTO.getId2();
		if(CommonTools.isInteger(id2) == false) {
		  return new AjaxResponse(Status.STATUS400, "This id2: " + id2 + " is not an integer.", null); 
		}
		
		List<String> testDTOList = testDTO.getEmails();
		if(testDTOList == null) {
			return new AjaxResponse(Status.STATUS400, "There's no email input.", null);
		} else if(testDTOList.size() > MAX_EMAIL_SUPPORT) {
			return new AjaxResponse(Status.STATUS400, "Email input numbers exceeded the limit: " + MAX_EMAIL_SUPPORT + ".", null);
		}
		for(String email : testDTOList) {
		  if(CommonTools.isEmail(email) == false) {
		    return new AjaxResponse(Status.STATUS400, "This email: " + email + " is invalid.", null); 
		  }
		}

		int num1 = Integer.parseInt(id1);
		int num2 = Integer.parseInt(id2);
		int total = num1 + num2;

		mailSender.sendAndSaveMail(num1, num2, total, testDTOList, logger);
		
		return new AjaxResponse(Status.SUCCESS, "", null); 	
	}
	
	@Autowired
	KeywordFinder keywordFinderService;
	
	@RequestMapping(value = "/searchEmailRecords", method = RequestMethod.GET, produces = {"application/json"})
	public List<Mailrecords> search(@RequestParam(value="keyword", defaultValue="") String keyword) {
      if("".equals(keyword)) {
        return keywordFinderService.findAll();
      }

	  return keywordFinderService.findByKeyword(keyword); 	
	}
}
