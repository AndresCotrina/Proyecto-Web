package com.app.web.unitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.app.web.entities.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductUnitTestController {
	private static final Logger logger 
	= LoggerFactory.getLogger(ProductUnitTestController.class);
	
    private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void TestGetProductos() throws Exception{
		int ID_FIRST = 1;
		this.mockMvc.perform(get("/createProductos"))
					.andExpect(status().isOk())
					.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].id", is(ID_FIRST)));
	}
	
	@Test
	public void testFindPetOK() throws Exception{
		int ID_SEARCH = 1;
		String NAME_PRODUCTO = "producto 1";
		String DESCRIPCION_PRODUCTO = "descripcio 1";
		String IMG_URL = "https://es.cravingsjournal.com/wp-content/uploads/2019/11/alitas-bbq-1.jpg";
		float PRECIO_PRODUCTO = 12.99f;
		mockMvc.perform(get("/getProducto" + ID_SEARCH))
				.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id",is(1)))
				.andExpect(jsonPath("$.nombre",is(NAME_PRODUCTO)))
				.andExpect(jsonPath("$.descripcion",is(DESCRIPCION_PRODUCTO)))
				.andExpect(jsonPath("$.img",is(IMG_URL)))
				.andExpect(jsonPath("$.precio",is(PRECIO_PRODUCTO)));
	}
	@Test
	public void testFindPetKO() throws Exception{
		int ID_SEARCH = 2;
		mockMvc.perform(get("/getProducto/" + ID_SEARCH)) // Finding object with ID = 666
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testCreatePet() throws Exception{
		String NAME_PRODUCTO = "producto Test";
		String DESCRIPCION_PRODUCTO = "descripcion Test";
		String IMG_URL = "https://es.cravingsjournal.com/wp-content/uploads/2019/11/alitas-bbq-1.jpg";
		float PRECIO_PRODUCTO = 5.99f;
		
		Producto p = new Producto(NAME_PRODUCTO, DESCRIPCION_PRODUCTO, PRECIO_PRODUCTO, IMG_URL);
		logger.info(p.toString());
		logger.info(om.writeValueAsString(p));
		 mockMvc.perform(post("/createProducto")
		            .content(om.writeValueAsString(p))
		            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		 			.andDo(print())
		 			.andExpect(jsonPath("$.name",is(NAME_PRODUCTO)))
		 			.andExpect(jsonPath("$.descripcion",is(DESCRIPCION_PRODUCTO)))
					.andExpect(jsonPath("$.img",is(IMG_URL)))
					.andExpect(jsonPath("$.precio",is(PRECIO_PRODUCTO)));
		
	}
	
	@Test
	public void deleteProducto() throws Exception{
		String NAME_PRODUCTO = "producto Test";
		String DESCRIPCION_PRODUCTO = "descripcion Test";
		String IMG_URL = "https://es.cravingsjournal.com/wp-content/uploads/2019/11/alitas-bbq-1.jpg";
		float PRECIO_PRODUCTO = 5.99f;
		
		Producto p = new Producto(NAME_PRODUCTO, DESCRIPCION_PRODUCTO, PRECIO_PRODUCTO, IMG_URL);
		ResultActions mvcActions = mockMvc.perform(post("/createProductos")
	            .content(om.writeValueAsString(p))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");
        mockMvc.perform(delete("/deleteProducto/" + id ))
        /*.andDo(print())*/
       .andExpect(status().isOk());
	}
}
