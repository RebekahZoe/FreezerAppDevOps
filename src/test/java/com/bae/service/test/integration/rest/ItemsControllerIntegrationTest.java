//package com.bae.service.test.integration.rest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.bae.persistence.domain.Items;
//import com.bae.persistence.repo.ItemsRepo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ItemsControllerIntegrationTest {
//
//	@Autowired
//	private MockMvc mock;
//
//	@Autowired
//	private ItemsRepo repo;
//
//	private ObjectMapper mapper = new ObjectMapper();
//
//	private long id;
//
//	private Items testItem;
//
//	private Items testItemWithID;
//
//	@Before
//	public void init() {
//		this.repo.deleteAll();
//
//		this.testItem = new Items("curry", 2);
//		this.testItemWithID = this.repo.save(this.testItem);
//		this.id = this.testItemWithID.getId();
//	}
//
//	@Test
//	public void testCreateItem() throws Exception {
//		String result = this.mock
//				.perform(request(HttpMethod.POST, "/createItems").contentType(MediaType.APPLICATION_JSON)
//						.content(this.mapper.writeValueAsString(testItem)).accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		assertEquals(this.mapper.writeValueAsString(testItemWithID), result);
//	}
//
//	@Test
//	public void testDeleteItem() throws Exception {
//		this.mock.perform(request(HttpMethod.DELETE, "/deleteItem/" + this.id)).andExpect(status().isOk());
//	}
//
//
//	
//
//	
//
//}
