package com.bae.service.test.unit.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.rest.FreezerController;
import com.bae.service.FreezerDoesntexistException;
import com.bae.service.FreezerService;
import com.bae.service.ItemDoesntexistException;

@RunWith(SpringRunner.class)
public class FreezerControllerTest {

	@InjectMocks
	private FreezerController controller;

	@Mock
	private FreezerService service;

	private List<Freezers> freezerList;

	private Freezers testFreezer;

	private Freezers testFreezerWithID;
	
	private Set<Items> items = new HashSet<>();
	
	private Items item;

	final long id = 1L;
	
	@Before
	public void init() {
		this.freezerList = new ArrayList<>();
		this.freezerList.add(testFreezer);
		this.testFreezer = new Freezers("Kitchen Freezer");
		this.testFreezerWithID = new Freezers(testFreezer.getFreezerName());
		this.testFreezerWithID.setId(id);
		this.item = new Items("curry",2);
		this.item.setId(id);
	}

	@Test
	public void createFreezerTest() {
		when(this.service.createFreezer(testFreezer)).thenReturn(testFreezerWithID);

		assertEquals(this.testFreezerWithID, this.controller.createFreezer(testFreezer));

		verify(this.service, times(1)).createFreezer(this.testFreezer);
	}

	@Test
	public void deleteFreezerTest() throws FreezerDoesntexistException, ItemDoesntexistException {
		this.controller.deleteFreezer(id);

		verify(this.service, times(1)).deleteFreezer(id);
	}

	@Test
	public void findFreezerByIDTest() throws FreezerDoesntexistException {
		when(this.service.findFreezerByID(this.id)).thenReturn(this.testFreezerWithID);

		assertEquals(this.testFreezerWithID, this.controller.getFreezer(this.id));

		verify(this.service, times(1)).findFreezerByID(this.id);
	}

	@Test
	public void getAllFreezersTest() {

		when(this.service.readFreezers()).thenReturn(this.freezerList);

		assertFalse("Controller has found no Freezers", this.controller.getAllFreezers().isEmpty());

		verify(this.service, times(1)).readFreezers();
	}
	@Test
	public void addItemToFreezerTest() throws FreezerDoesntexistException {
		when(this.service.addItemToFreezer(this.id, this.item)).thenReturn(testFreezerWithID);

		assertEquals(this.testFreezerWithID, this.controller.addItemToFreezer(this.id,this.item));

		verify(this.service, times(1)).addItemToFreezer(this.id,this.item);
	
	}
	
	@Test
	public void getItemsFromFreezerTest() throws FreezerDoesntexistException {
		when(this.service.getItemsFromFreezer(this.id)).thenReturn(items);
		assertEquals(this.items,this.service.getItemsFromFreezer(this.id));
		verify(this.service,times(1)).getItemsFromFreezer(this.id);
		
		
	}


}
