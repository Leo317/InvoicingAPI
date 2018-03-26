package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import org.springframework.web.context.WebApplicationContext;

import com.example.model.Products;
import com.example.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

//	@Mock
//	@Autowired
//	private ClientService clientService;

	@InjectMocks
	@Autowired
	private ClientController clientController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(clientController)
				.build();
	}
    
    @Test
    public void testFindOrderableProducts() throws Exception {
    	
//    	List<Products> test = Arrays.asList(
//    			new Products(5, 55, "test1", 30, 50, true),
//    			new Products(3, 33, "test2", 10, 30, true));
    	
//    	when(clientServ.getOrderableProductsList()).thenReturn(test);
    	
        mockMvc.perform(get("/client/getOrderableProductsList")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
                
                .andExpect(jsonPath("$.result[0].id", is(803)))
                .andExpect(jsonPath("$.result[0].productId", is(55)))
                .andExpect(jsonPath("$.result[0].productName", is("test1")))
                .andExpect(jsonPath("$.result[0].price", is(30)))
                .andExpect(jsonPath("$.result[0].quantity", is(15)))
                .andExpect(jsonPath("$.result[0].auction", is(true)))
                
                .andExpect(jsonPath("$.result[1].id", is(804)))
                .andExpect(jsonPath("$.result[1].productId", is(33)))
                .andExpect(jsonPath("$.result[1].productName", is("test2")))
                .andExpect(jsonPath("$.result[1].price", is(10)))
                .andExpect(jsonPath("$.result[1].quantity", is(30)))
                .andExpect(jsonPath("$.result[1].auction", is(true)))
                
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                ;
        
//        verify(clientServ, times(1)).getOrderableProductsList();
//        verifyNoMoreInteractions(clientServ);
//        verify(clientServ).getOrderableProductsList();
    }
    
    @Test
    public void testOrderProudcts() throws Exception {
    	List<Products> temp = new ArrayList<>();
    	Products products = new Products();
    	products.setProductName("test1");
    	products.setQuantity(5);
        temp.add(products);
        
//        when(clientServ.getProductQuantity(products.getProductName())).thenReturn(50);
        
        mockMvc.perform(post("/client/orderProducts")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(asJsonString(temp))
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.message", Matchers.is("")))
               .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ;
        
//        mockMvc
//        .perform(get("/share/getOrderList")
//                .accept(MediaType.APPLICATION_JSON_UTF8))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.message", Matchers.is("")))
//        .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
//        
//        .andExpect(jsonPath("$.result[0].commodity[0].productName", is("test1")))
//        .andExpect(jsonPath("$.result[0].commodity[0].quantity", is(5)))
//        
//        .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
//        ;
        
//        verify(clientServ, times(1)).orderProudcts(1, temp);
        
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//        		.post("/client/orderProducts")
//        		.content(asJsonString(temp))
//        		.accept(MediaType.APPLICATION_JSON))
//        		.andExpect(status().isOk())
//        		.andReturn();
//		int status = result.getResponse().getStatus();
//		Assert.assertEquals("Âe’`",200,status);
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
