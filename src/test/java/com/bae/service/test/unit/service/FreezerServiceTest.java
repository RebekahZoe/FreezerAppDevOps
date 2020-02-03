package com.bae.service.test.unit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.persistence.repo.ItemsRepo;
import com.bae.service.FreezerDoesntexistException;
import com.bae.service.FreezerService;
import com.bae.service.ItemDoesntexistException;
import com.bae.service.ItemIsNotInFreezerException;
import com.bae.service.ItemService;

@RunWith(SpringRunner.class)
public class FreezerServiceTest {
	

	private List<Freezers> freezers;
	private Freezers freezer;
	private Freezers freezerWithId;
	private Long id;
	private Items item;
	private Items itemWithId;
	private Set<Items> items = new HashSet<>();
 	@InjectMocks
    private FreezerService service;
 	@Mock
 	private ItemService itemService;

    @Mock
    private FreezerRepo repo;
    @Mock
    private ItemsRepo itemRepo;
    
    @Before
    public void setup() {
    	this.freezers = new ArrayList<>();
    	this.freezer = new Freezers("kitchen freezer");
    	this.freezers.add(freezer);
    	this.freezerWithId = new Freezers(freezer.getFreezerName()); 
    	this.freezerWithId.setId(1L);
    	this.id = freezerWithId.getId();
    	this.item = new Items("curry", 2);
		this.itemWithId = new Items(item.getItemName(),item.getQuantity());
		this.itemWithId.setId(1L);
		
    }
	@Test
	public void readFreezersTest() { 
        Mockito.when(repo.findAll()).thenReturn(freezers);
        assertTrue("Returned no freezers", this.service.readFreezers().size()>0);
			
	}
	
	@Test
	public void addFreezerTest() {
		
		when(this.repo.save(freezer)).thenReturn(freezerWithId);

		assertEquals(this.freezerWithId, this.service.createFreezer(freezer));

		verify(this.repo, times(1)).save(this.freezer);
		
	}
	
	@Test
	public void findFreezerByIDTest() throws FreezerDoesntexistException {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.freezerWithId));

		assertEquals(this.freezerWithId, this.service.findFreezerByID(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void deleteFreezerTest() throws FreezerDoesntexistException, ItemDoesntexistException {
		
		when(this.repo.existsById(id)).thenReturn(true);  
		this.service.deleteFreezer(id);
		
		verify(this.repo, times(1)).deleteById(this.id);
		verify(this.repo, times(2)).existsById(id); 
	}
	
	@Test
	public void deleteFreezerByNameTest() throws FreezerDoesntexistException, ItemDoesntexistException {
		
		when(this.repo.findByFreezerName("kitchen freezer")).thenReturn(freezerWithId);
		when(this.repo.existsById(id)).thenReturn(true);
		
		this.service.deleteFreezer(id);
		
		verify(this.repo, times(1)).deleteById(this.id);
		verify(this.repo, times(2)).existsById(id); 
	}
	
	@Test
	public void addItemToFreezersTest() throws FreezerDoesntexistException {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.freezerWithId));
		Freezers updateFreezer = this.service.findFreezerByID(id);
		
		
		
		when(this.itemRepo.findById(this.id)).thenReturn(Optional.of(itemWithId));
		
		Mockito.when(this.itemService.createItem(item)).thenReturn(itemWithId);
		
		
		
		when(this.itemRepo.save(item)).thenReturn(itemWithId);
		when(this.repo.saveAndFlush(freezerWithId)).thenReturn(updateFreezer);
		assertEquals(updateFreezer,this.service.addItemToFreezer(this.id, item));
		
	}
	@Test
	public void getItemsFromFreezerTest() throws FreezerDoesntexistException {
		Mockito.when(this.repo.findById(this.freezerWithId.getId())).thenReturn(Optional.of(this.freezerWithId));
		assertEquals(this.items,this.service.getItemsFromFreezer(this.freezerWithId.getId()));
		
	}
	
	@Test
	public void deleteItemFromFreezerTest() throws FreezerDoesntexistException, ItemDoesntexistException, ItemIsNotInFreezerException {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.freezerWithId));
		Freezers currentFreezer = this.service.findFreezerByID(id);
		this.items.add(itemWithId);
		currentFreezer.setItems(items);

		when(this.itemRepo.findByItemName("curry")).thenReturn(this.itemWithId);
		when(this.itemService.deleteItem(1L)).thenReturn(true);
		
		this.service.deleteItemFromFreezer("curry", 1L);
		
		verify(this.itemRepo, times(2)).findByItemName("curry");
		verify(this.itemService, times(1)).deleteItem(1L);
	}

}
