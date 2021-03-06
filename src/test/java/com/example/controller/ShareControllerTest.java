package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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



// ===== test quantity value is not Integer start =====
        mockMvc.perform(get("/share/getOrderList?id=2.")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.status", Matchers.is("STATUS400")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ;
// ===== test quantity value is not Integer end =====
        
// ===== test normal case start =====
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
                
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                ;
// ===== test normal case end =====

// ===== test normal case start =====
    	mockMvc
	        .perform(get("/share/getOrderList?id=2")
	                .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.message", Matchers.is("")))
	        .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
	        
	        .andExpect(jsonPath("$.result[0].orderId", is(2)))
            .andExpect(jsonPath("$.result[0].commodity[0].productName", is("test1")))
            .andExpect(jsonPath("$.result[0].commodity[0].price", is(30)))
            .andExpect(jsonPath("$.result[0].commodity[0].quantity", is(5)))
            .andExpect(jsonPath("$.result[0].commodity[0].total", is(150)))
	        
	        .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
	        ;
// ===== test normal case end =====
    }
}
