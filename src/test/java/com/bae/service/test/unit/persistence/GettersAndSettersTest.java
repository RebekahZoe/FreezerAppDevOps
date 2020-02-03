package com.bae.service.test.unit.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;

public class GettersAndSettersTest {

	private Freezers freezer = new Freezers();
	private Items item = new Items();

	@Before
	public void setup() {
		this.freezer = new Freezers();
		this.item = new Items();
	}

	@Test
	public void setFreezerNameTest() {
		freezer.setFreezerName("kitchen freezer");
		Assert.assertEquals("kitchen freezer", freezer.getFreezerName());
	}
	
	@Test
	public void getIdTest() {
		freezer.setId(1L);
		assertEquals(1L,freezer.getId());
		
		item.setId(1L);
		assertEquals(1L,item.getId());
	}

	@Test
	public void getItemsTest() {
		Set<Items> items =  new HashSet<>();
		Set<Items> itemsActual = freezer.getItems();
		Assert.assertEquals(items, itemsActual);
	}

	@Test
	public void setItemsTest() {
		Set<Items> items = new HashSet<>();
		freezer.setItems(items);
	}

	@Test
	public void setItemNameTest() {
		Items item = new Items();
		item.setItemName("curry");
		Assert.assertEquals("curry", item.getItemName());
	}

	@Test
	public void setItemQuantityTest() {
		Items item = new Items();
		item.setQuantity(3);
		Assert.assertEquals(3, item.getQuantity());
	}
	
	@Test
	public void constructorTest() {
		Freezers freezer = new Freezers("kitchen freezer");
		assertEquals("kitchen freezer",freezer.getFreezerName());
		
		Items item = new Items("curry",2);
		assertEquals("curry",item.getItemName());
		assertEquals(2,item.getQuantity());
		
		Items item2 = new Items("curry");
		assertEquals("curry",item2.getItemName());
		
		Freezers freezer2 = new Freezers("kitchen freezer",1L);

		assertEquals("kitchen freezer",freezer.getFreezerName());

		assertEquals(1L,freezer2.getId());
		
		assertEquals("Freezers [id=null, freezerName=kitchen freezer, items=[]]",freezer.toString());
		assertEquals("Items [id=null, itemName=curry, quantity=2]",item.toString());
	}
	
	@Test
	public void testHashcode() {
		Freezers x = new Freezers("kitchen freezer");  
		Freezers y = new Freezers("kitchen freezer"); 
	  Assert.assertTrue(x.equals(y) && y.equals(x));
	  Assert.assertTrue(x.hashCode() == y.hashCode());
	}

}
