package com.bae.service.test.unit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.ItemsRepo;
import com.bae.service.ItemService;
import com.bae.service.ItemDoesntexistException;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

	private List<Items> items;
	private Items item;
	private Items itemWithId;
	final Long id = 1L;
	
 	@InjectMocks
    private ItemService service;

    @Mock 
    private ItemsRepo repo;
    
    @Before
    public void setup() {
    	this.items = new ArrayList<>();
    	this.item = new Items("curry",3);
    	this.items.add(item);
    	this.itemWithId = new Items(item.getItemName(),item.getQuantity()); 
    	this.itemWithId.setId(id);
    	this.itemWithId.setFreezer_id(id);
    }
	@Test
	public void readItemsTest() { 
        Mockito.when(repo.findAll()).thenReturn(items);
        assertTrue("Returned no items", this.service.readItems().size()>0); 
			
	}
	 
	@Test
	public void addItemsTest() {
		
		when(this.repo.save(item)).thenReturn(itemWithId);

		assertEquals(this.itemWithId, this.service.createItem(item));

		verify(this.repo, times(1)).save(this.item);
		 
	}
	@Test
	public void deleteItemTest() throws ItemDoesntexistException {
		when(this.repo.existsById(id)).thenReturn(true, false);
		this.service.deleteItem(id);
		
		verify(this.repo, times(1)).deleteById(this.id);
		verify(this.repo, times(2)).existsById(id);
	}
	
	
	

}
	
