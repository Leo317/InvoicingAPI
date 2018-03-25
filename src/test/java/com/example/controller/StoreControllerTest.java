package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Products;
import com.example.service.ProductFinder;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoreControllerTest {
	private MockMvc mockMvc;
	
	@Mock
	ProductFinder productFinder;
	
	@InjectMocks
	private StoreController storeController;
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(storeController)
                
                .build();
    }
    
    @Test
    public void testPurchase() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(storeController)
                
                .build();
    }
    
    @Test
    public void testList() throws Exception{
    	List<Products> productList = Arrays.asList(
    	  new Products(1, 1, "Sausage", 42, 210, false),
    	  new Products(2, 2, "Potato Snack", 110, 23, true));        

        when(productFinder.findAll()).thenReturn(productList);

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
                .andExpect(content()
                  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.result[0].productId", is(1)))
                .andExpect(jsonPath("$.result[0].productName", is("Sausage")))
                .andExpect(jsonPath("$.result[0].price", is(42)))
                .andExpect(jsonPath("$.result[0].quantity", is(210)))
                .andExpect(jsonPath("$.result[0].auction", is(false)))
                .andExpect(jsonPath("$.result[1].productId", is(2)))
                .andExpect(jsonPath("$.result[1].productName", is("Potato Snack")))
                .andExpect(jsonPath("$.result[1].price", is(110)))
                .andExpect(jsonPath("$.result[1].quantity", is(23)))
                .andExpect(jsonPath("$.result[1].auction", is(true)));

        verify(productFinder, times(1)).findAll();
        verifyNoMoreInteractions(productFinder);
    }
}
