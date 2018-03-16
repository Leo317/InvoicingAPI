package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MailrecordsDaoImpl;
import com.example.model.Mailrecords;

@Service("keywordFinderService")
public class KeywordFinder {
  
  @Autowired
  private MailrecordsDaoImpl mailRecordsDao;
	
//  public List<SearchDTO> findKeyword(String keyword) {
//    List<Mailrecords> mailRecordsList = new  ArrayList<Mailrecords>();
//	mailRecordsList = mailRecordsDao.findAll();
//	List<SearchDTO> resultList = new ArrayList<SearchDTO>();
//	for(Mailrecords record : mailRecordsList) {
//	  if(record.getContent().contains(keyword) ||
//		record.getEmail().contains(keyword)) {
//	    SearchDTO dto = new SearchDTO();
//	    dto.setContent(record.getContent());
//	    dto.setEmail(record.getEmail());
//	    SimpleDateFormat dateFormat = 
//	      new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//	    dto.setTime(dateFormat.format(record.getTime()));
//	    resultList.add(dto);
//	  }
//	}
//	
//	//Search result ordered by sending time descending.
//	Collections.sort(resultList, Collections.reverseOrder());
//	return resultList;	  
//  }
  @Transactional
  public List<Mailrecords> findAll() {
    return mailRecordsDao.findAll();
  }
  @Transactional
  public List<Mailrecords> findByKeyword(String keyword) {
    return mailRecordsDao.findByKeyword(keyword);
  }

}
