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

import org.apache.commons.lang3.math.NumberUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Products;
import com.example.service.impl.ShareServiceImpl;
import com.example.view.CommodityDTO;
import com.example.view.OrdersDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShareControllerTest {
	private MockMvc mockMvc;

    @Mock
    private ShareServiceImpl shareServ;

    @InjectMocks
    private ShareController userController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                
                .build();
    }
    
    @Test
    public void testFindOne() throws Exception {
    	
    	List<CommodityDTO> temp1 = new ArrayList<CommodityDTO>();
    	CommodityDTO cto1 = new CommodityDTO("test1", 35, 3, 105);
    	temp1.add(cto1);
    	cto1 = new CommodityDTO("test2", 50, 2, 100);
    	temp1.add(cto1);
    	
    	List<CommodityDTO> temp2 = new ArrayList<CommodityDTO>();
    	cto1 = new CommodityDTO("test56", 10, 3, 30);
    	temp2.add(cto1);
    	
    	List<OrdersDTO> test = Arrays.asList(
    			new OrdersDTO(1, temp1),
    			new OrdersDTO(2, temp2));
    	
    	when(shareServ.findAll()).thenReturn(test);

        mockMvc
                .perform(get("/share/getOrderList")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
                
                .andExpect(jsonPath("$.result[0].orderId", is(1)))
                .andExpect(jsonPath("$.result[0].commodity[0].productName", is("test1")))
                .andExpect(jsonPath("$.result[0].commodity[0].price", is(35)))
                .andExpect(jsonPath("$.result[0].commodity[0].quantity", is(3)))
                .andExpect(jsonPath("$.result[0].commodity[0].total", is(105)))
                
                .andExpect(jsonPath("$.result[0].commodity[1].productName", is("test2")))
                .andExpect(jsonPath("$.result[0].commodity[1].price", is(50)))
                .andExpect(jsonPath("$.result[0].commodity[1].quantity", is(2)))
                .andExpect(jsonPath("$.result[0].commodity[1].total", is(100)))
                
                .andExpect(jsonPath("$.result[1].orderId", is(2)))
                .andExpect(jsonPath("$.result[1].commodity[0].productName", is("test56")))
                .andExpect(jsonPath("$.result[1].commodity[0].price", is(10)))
                .andExpect(jsonPath("$.result[1].commodity[0].quantity", is(3)))
                .andExpect(jsonPath("$.result[1].commodity[0].total", is(30)))
                
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                ;
        verify(shareServ, times(1)).findAll();
        verifyNoMoreInteractions(shareServ);
        
        
        
        
        
        List<CommodityDTO> temp = new ArrayList<CommodityDTO>();
    	cto1 = new CommodityDTO("777", 1, 1, 1);
    	temp.add(cto1);
    	
    	List<OrdersDTO> one = Arrays.asList(new OrdersDTO(1, temp));
    	
    	when(shareServ.findOne(1)).thenReturn(one);
    	
    	mockMvc
	        .perform(get("/share/getOrderList?id=1")
	                .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.message", Matchers.is("")))
	        .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
	        
	        .andExpect(jsonPath("$.result[0].orderId", is(1)))
	        .andExpect(jsonPath("$.result[0].commodity[0].productName", is("777")))
	        .andExpect(jsonPath("$.result[0].commodity[0].price", is(1)))
	        .andExpect(jsonPath("$.result[0].commodity[0].quantity", is(1)))
	        .andExpect(jsonPath("$.result[0].commodity[0].total", is(1)))
	        
	        .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
	        ;
    	
    	verify(shareServ, times(1)).findOne(1);
        verifyNoMoreInteractions(shareServ);
    }
}
