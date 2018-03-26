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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Products;
import com.example.view.ProductsDTO;
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
        mockMvc.perform(get("/client/getOrderableProductsList")
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
                
                .andExpect(jsonPath("$.result[0].productId", is(55)))
                .andExpect(jsonPath("$.result[0].productName", is("test1")))
                .andExpect(jsonPath("$.result[0].price", is(30)))
//                .andExpect(jsonPath("$.result[0].quantity", is(49990)))
                .andExpect(jsonPath("$.result[0].auction", is(true)))
                
                .andExpect(jsonPath("$.result[1].productId", is(33)))
                .andExpect(jsonPath("$.result[1].productName", is("test2")))
                .andExpect(jsonPath("$.result[1].price", is(10)))
//                .andExpect(jsonPath("$.result[1].quantity", is(29990)))
                .andExpect(jsonPath("$.result[1].auction", is(true)))
                
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                ;
    }
    
    @Test
    public void testOrderProudcts() throws Exception {
// ===== test productsDTOs is null start =====
    	List<Products> temp = new ArrayList<>();
 
        mockMvc.perform(post("/client/orderProducts")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(asJsonString(temp))
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.status", Matchers.is("ERROR")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ;
// ===== test productsDTOs is null end =====

// ===== test quantity value is not Integer start =====
    	List<ProductsDTO> temp1 = new ArrayList<>();
    	ProductsDTO products1 = new ProductsDTO();
    	products1.setProductName("test1");
    	products1.setQuantity("5.");
        temp1.add(products1);
 
        mockMvc.perform(post("/client/orderProducts")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(asJsonString(temp1))
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.status", Matchers.is("STATUS400")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ;
// ===== test quantity value is not Integer end =====
        
// ===== test product name is null start =====
    	List<Products> temp2 = new ArrayList<>();
    	Products products2 = new Products();
    	products2.setProductName(null);
    	products2.setQuantity(5);
        temp2.add(products2);
 
        mockMvc.perform(post("/client/orderProducts")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(asJsonString(temp2))
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.status", Matchers.is("STATUS400")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ;
// ===== test product name is null end =====
    	
// ===== test product name's length is too longer start =====
    	List<Products> temp3 = new ArrayList<>();
    	Products products3 = new Products();
    	products3.setProductName("test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1");
    	products3.setQuantity(5);
        temp3.add(products3);
 
        mockMvc.perform(post("/client/orderProducts")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(asJsonString(temp3))
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.status", Matchers.is("STATUS400")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ;
// ===== test product name's length is too longer end =====
        
// ===== test over quantity start =====
        List<Products> temp4 = new ArrayList<>();
    	Products products4 = new Products();
    	products4.setProductName("test1");
    	products4.setQuantity(99999999);
        temp4.add(products4);
 
        mockMvc.perform(post("/client/orderProducts")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(asJsonString(temp4))
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
//               .andExpect(jsonPath("$.message", Matchers.is("")))
               .andExpect(jsonPath("$.status", Matchers.is("STATUS400")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ; 
// ===== test over quantity end =====

// ===== test normal case start =====
    	List<Products> temp5 = new ArrayList<>();
    	Products products6 = new Products();
    	products6.setProductName("test1");
    	products6.setQuantity(5);
        temp5.add(products6);
 
        mockMvc.perform(post("/client/orderProducts")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(asJsonString(temp5))
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType
                    (MediaType.APPLICATION_JSON_UTF8_VALUE))
               .andExpect(jsonPath("$.message", Matchers.is("")))
               .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
               .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
               ;
// ===== test normal case end =====
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
