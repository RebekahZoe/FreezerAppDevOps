package com.bae.service.test.integration.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.ItemsRepo;
import com.bae.service.FreezerDoesntexistException;
import com.bae.service.ItemDoesntexistException;
import com.bae.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsServiceIntegrationTest {

	@Autowired
	private ItemService service;

	@Autowired
	private ItemsRepo repo;

	private Items testItem;

	private Items testItemWithID;

	@Before
	public void init() {
		this.testItem = new Items("curry", 2);

		this.repo.deleteAll();
		this.testItemWithID = this.repo.save(this.testItem);
	}

	@Test
	public void testCreateItem() {
		assertEquals(this.testItemWithID,this.service.createItem(testItem));
	}
	
	@Test
	public void testDeleteItem() throws ItemDoesntexistException {
		assertThat(this.service.deleteItem(this.testItemWithID.getId())).isFalse();
	}
	
	@Test
	public void testReadItems() {
		assertThat(this.service.readItems()).isEqualTo(Arrays.asList(new Items[] { this.testItemWithID }));
	}
	

	
	@Test
	public void testUpdateItem() throws ItemDoesntexistException, FreezerDoesntexistException {
		Items newItem = new Items("curry", 1);
		Items updatedItem = new Items(newItem.getItemName(), newItem.getQuantity());
		updatedItem.setId(this.testItemWithID.getId());

		assertThat(this.service.updateItem(newItem, this.testItemWithID.getId(), null)).isEqualTo(updatedItem);
	}

}
