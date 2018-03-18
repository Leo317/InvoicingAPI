package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;

@RestController
public class StoreController {
  @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = {"application/json"})
    public Response purchase(@RequestBody Products[] products) {

			
	  return new AjaxResponse(Status.SUCCESS, "", null);
  }
  
  @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
  public List<Products> list(@RequestParam(value="keyword", defaultValue="") String keyword) {
	return null;
  }
  

}
