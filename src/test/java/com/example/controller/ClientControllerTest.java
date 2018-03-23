package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.IClientService;
import com.example.view.ProductsDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientControllerTest {
//	//text Double
//		@Mock
//		IClientService clientServ;
//
//		// 要被測試的，也就是SUT(Controller層)
//		@InjectMocks
//		private ClientController toDoController;
//		
//		@Before
//		public void setup(){
//			MockitoAnnotations.initMocks(this);
//		}
		
	private MockMvc mockMvc;

    @Mock
    private IClientService clientServ;

    @InjectMocks
    private ClientController userController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                
                .build();
    }
    
    @Test
    public void testFindOrderableProducts() throws Exception {
    	
//    	when(userController.findOrderableProducts()).thenReturn
//    				(AjaxResponse(Status.SUCCESS, "", clientServ.getOrderableProductsList()));

        mockMvc.perform(get("/client/getOrderableProductsList")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("")));

        verify(clientServ).getOrderableProductsList();
    }
}
