package com.example.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import com.example.model.Products;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class StoreControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	@Autowired
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
        
		List<Products> testList = new ArrayList<>();
		Products products = new Products();
		products.setProductId(5);
		products.setProductName("Hamburger");
		products.setPrice(60);
		products.setQuantity(100);
		products.setAuction(true);
		testList.add(products);

		//insert a new product
        mockMvc.perform(post("/purchase")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(testList))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType
                     (MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                ;        

        //update
		Products products2 = new Products();
		products2.setProductId(55);
		products2.setProductName("test1");
		products2.setPrice(22);
		products2.setQuantity(50000);
		products2.setAuction(true);
	    
        mockMvc.perform(post("/purchase/55")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(products2))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
    }
    
    @Test
    public void testList() throws Exception{

		mockMvc.perform(get("/list"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.message", Matchers.is("")))
				.andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.*", Matchers.hasSize(3)))
				.andExpect(jsonPath("$.result[0].productId", is(33)))
				.andExpect(jsonPath("$.result[0].productName", is("test2")))
				.andExpect(jsonPath("$.result[0].price", is(10)))
				.andExpect(jsonPath("$.result[0].auction", is(true)))
				.andExpect(jsonPath("$.result[1].productId", is(55)))
				.andExpect(jsonPath("$.result[1].productName", is("test1")))
				.andExpect(jsonPath("$.result[1].price", is(30)))
				.andExpect(jsonPath("$.result[1].auction", is(true)))
				;
        
        mockMvc.perform(get("/list?keyword=e"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message", Matchers.is("")))
        .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
        .andExpect(content()
          .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
		.andExpect(jsonPath("$.result[0].productId", is(33)))
		.andExpect(jsonPath("$.result[0].productName", is("test2")))
		.andExpect(jsonPath("$.result[0].price", is(10)))
		.andExpect(jsonPath("$.result[0].auction", is(true)))
		.andExpect(jsonPath("$.result[1].productId", is(55)))
		.andExpect(jsonPath("$.result[1].productName", is("test1")))
		.andExpect(jsonPath("$.result[1].price", is(30)))
		.andExpect(jsonPath("$.result[1].auction", is(true)))
		;
        
        String auctionStr = "tyu";
		String[] strArray = 
  		  { "The auction parameter: ", auctionStr, " is invalid. It should be \"true\" or \"false\"" };
        
        mockMvc.perform(get("/list?keyword=e&auction=tyu"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message", Matchers.is(StringUtils.join(strArray))))
        .andExpect(jsonPath("$.status", Matchers.is("STATUS400")))
        .andExpect(content()
          .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.result", Matchers.is(nullValue())));        
        
        mockMvc.perform(get("/list?keyword=e&auction=true"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message", Matchers.is("")))
        .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
        .andExpect(content()
          .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
		.andExpect(jsonPath("$.result[0].productId", is(33)))
		.andExpect(jsonPath("$.result[0].productName", is("test2")))
		.andExpect(jsonPath("$.result[0].price", is(10)))
		.andExpect(jsonPath("$.result[0].auction", is(true)))
		.andExpect(jsonPath("$.result[1].productId", is(55)))
		.andExpect(jsonPath("$.result[1].productName", is("test1")))
		.andExpect(jsonPath("$.result[1].price", is(30)))
		.andExpect(jsonPath("$.result[1].auction", is(true)))
		;
        
    }
    
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
