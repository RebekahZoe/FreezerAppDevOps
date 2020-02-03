package com.bae.service.test.integration.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.persistence.repo.ItemsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FreezerControllerIntegerationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private FreezerRepo repo;

	@Autowired
	private ItemsRepo itemRepo;

	private ObjectMapper mapper = new ObjectMapper();

	private long id;

	private Freezers testFreezer;

	private Freezers testFreezerWithID;

	private Items testItem;

	private Items testItemWithID;

	private Set<Items> setItems = new HashSet<>();

	@Before
	public void init() {
		this.repo.deleteAll();

		this.testFreezer = new Freezers("kitchen freezer");
		this.testFreezerWithID = this.repo.save(this.testFreezer);
		this.id = this.testFreezerWithID.getId();
		this.testItem = new Items("curry",2);
		this.testItemWithID = this.itemRepo.save(this.testItem);

	}

	@Test
	public void testCreateFreezer() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/createFreezer").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testFreezer)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testFreezerWithID), result);
	}

	@Test
	public void testDeleteFreezer() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/deleteFreezer/" + this.id)).andExpect(status().isOk());
	}

	@Test
	public void testFindAllFreezers() throws Exception {
		List<Freezers> freezerList = new ArrayList<>();
		freezerList.add(this.testFreezerWithID);

		String content = this.mock.perform(request(HttpMethod.GET, "/getAllFreezers").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(freezerList), content);
	}

	@Test
	public void testFindFreezerByID() throws Exception {
		this.mock.perform(request(HttpMethod.GET, "/getFreezer/" + this.id)).andExpect(status().isOk());
	}

	@Test
	public void testAddItem() throws Exception {
		Freezers testFreezerWithItems = testFreezerWithID;
		Set<Items> itemSet=  new HashSet<Items>();
		itemSet.add(this.testItem);
		testFreezerWithItems.setItems(itemSet);
		String result = this.mock
				.perform(request(HttpMethod.PATCH, "/addItem/"+ this.id).contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testItem)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testFreezerWithItems), result);
	}

	@Test
	public void testGetItemsFromFreezer() throws Exception {

		String content = this.mock.perform(request(HttpMethod.GET, "/getItemsFromFreezer/"+this.id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(setItems), content);

	}

}
