package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.service.impl.ShareServiceImpl;
import com.example.view.CommodityDTO;
import com.example.view.OrdersDTO;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class ShareControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

//    @Mock
//    private ShareServiceImpl shareServ;

    @InjectMocks
    @Autowired
    private ShareController shareController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(shareController)
                .build();
    }
    
    @Test
    public void testFindOne() throws Exception {
//    	[
//    		{
//    			"productName" : "test1",
//    			"quantity" : 3
//    		},
//    		{
//    			"productName" : "test1",
//    			"quantity" : 2
//    		},
//    		{
//    			"productName" : "test2",
//    			"quantity" : 5
//    		}
//    	]
    	
//    	[
//    		{
//    			"productName" : "test2",
//    			"quantity" : 5
//    		}
//    	]
    	
        mockMvc
                .perform(get("/share/getOrderList")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
                
                .andExpect(jsonPath("$.result[0].orderId", is(1)))
                .andExpect(jsonPath("$.result[0].commodity[0].productName", is("test1")))
                .andExpect(jsonPath("$.result[0].commodity[0].price", is(30)))
                .andExpect(jsonPath("$.result[0].commodity[0].quantity", is(5)))
                .andExpect(jsonPath("$.result[0].commodity[0].total", is(150)))
                
                .andExpect(jsonPath("$.result[0].commodity[1].productName", is("test2")))
                .andExpect(jsonPath("$.result[0].commodity[1].price", is(10)))
                .andExpect(jsonPath("$.result[0].commodity[1].quantity", is(5)))
                .andExpect(jsonPath("$.result[0].commodity[1].total", is(50)))
                
                .andExpect(jsonPath("$.result[1].orderId", is(2)))
                .andExpect(jsonPath("$.result[1].commodity[0].productName", is("test2")))
                .andExpect(jsonPath("$.result[1].commodity[0].price", is(10)))
                .andExpect(jsonPath("$.result[1].commodity[0].quantity", is(5)))
                .andExpect(jsonPath("$.result[1].commodity[0].total", is(50)))
                
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                ;

    	mockMvc
	        .perform(get("/share/getOrderList?id=1")
	                .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.message", Matchers.is("")))
	        .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
	        
	        .andExpect(jsonPath("$.result[0].orderId", is(1)))
	        .andExpect(jsonPath("$.result[0].commodity[0].productName", is("test1")))
	        .andExpect(jsonPath("$.result[0].commodity[0].price", is(30)))
	        .andExpect(jsonPath("$.result[0].commodity[0].quantity", is(5)))
	        .andExpect(jsonPath("$.result[0].commodity[0].total", is(150)))
	        
	        .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
	        ;
    }
}
