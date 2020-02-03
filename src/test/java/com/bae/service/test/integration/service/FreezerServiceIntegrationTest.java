//package com.bae.service.test.integration.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.assertj.core.util.Arrays;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.bae.persistence.domain.Freezers;
//import com.bae.persistence.domain.Items;
//import com.bae.persistence.repo.FreezerRepo;
//import com.bae.service.FreezerDoesntexistException;
//import com.bae.service.FreezerService;
//
//import com.bae.service.ItemDoesntexistException;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class FreezerServiceIntegrationTest {
//
//	@Autowired
//	private FreezerService service;
//
//	@Autowired
//	private FreezerRepo repo;
//
//	private Freezers testFreezer;
//
//	private Freezers testFreezerWithID;
//	private Freezers toUpdate;
//	
//	private Items item;
//	
//	private Set<Items> itemSet= new HashSet<>();
//	
//	private Freezers freezerWithItem;
//
//	@Before
//	public void init() {
//		this.repo.deleteAll();
//		this.testFreezer = new Freezers("kitchen freezer");
//		this.testFreezerWithID = this.repo.save(this.testFreezer);
//		this.toUpdate = this.repo.save(this.testFreezer);
//		this.item = new Items("curry",2);
//
//		this.item.setId(5L);
//
//		toUpdate.getItems().add(item);
//		
//		this.freezerWithItem = this.repo.save(this.testFreezer);
//
//	}
//
//	@Test 
//	public void testCreateFreezer() {
//		assertEquals(this.testFreezerWithID, this.service.createFreezer(testFreezer));
//	}
//
//	@Test
//	public void testDeleteFreezer() throws FreezerDoesntexistException, ItemDoesntexistException {
//		assertThat(this.service.deleteFreezer(this.testFreezerWithID.getId())).isFalse();
//	}
//
//	@Test
//	public void testGetAllFreezers() {
//		assertThat(this.service.readFreezers()).isEqualTo(Arrays.asList(new Freezers[] { this.testFreezerWithID }));
//	}
//	
//	@Test
//	public void testGetFreezerByID() throws FreezerDoesntexistException {
//		assertThat(this.service.findFreezerByID(this.testFreezerWithID.getId())).isEqualTo(this.testFreezerWithID);
//	}
//	@Test
//	public void testAddItemToFreezer() throws FreezerDoesntexistException {
//		assertThat(this.service.addItemToFreezer(this.testFreezerWithID.getId(),new Items("curry",2))).isEqualTo(toUpdate);
//	}
//	@Test
//	public void testGetItemsFromFreezer() throws FreezerDoesntexistException {
//		assertThat(this.service.getItemsFromFreezer(this.toUpdate.getId())).isEqualTo(freezerWithItem.getItems());
//	}
//}
