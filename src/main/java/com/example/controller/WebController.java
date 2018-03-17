package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Summary;
import com.example.repo.SummaryRepository;
import com.example.service.CommonTools;

@RestController
public class WebController {

	@Autowired
	SummaryRepository repo;
	
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@RequestMapping(value = "/total", method = RequestMethod.GET, produces = {"application/json"})
	public Summary process(@RequestParam(value="id1") int id1,
			 			   @RequestParam(value="id2") int id2){
		
	    Summary summary = new Summary(id1, id2);
	    repo.save(summary);
	    logger.debug(id1 + " + " + id2 + " = " + summary.getSummary() + "\n");
		return summary;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
	public List<Summary> findAll() {
		return CommonTools.copyIterator(repo.findAll().iterator()); 	
	}
	
	@RequestMapping("/")
	public String find(){
		return "HHH";
	}

}

