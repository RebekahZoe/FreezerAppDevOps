package com.bae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.ItemsRepo;

@Service
public class ItemService {

	private ItemsRepo repo;

	
	public ItemService(ItemsRepo repo) {
		super();
		this.repo = repo;
	}

	public List<Items> readItems() {
		return this.repo.findAll();
	}

	public Items createItem(Items item) {
		return this.repo.save(item);
	}

	public boolean deleteItem(Long id) throws ItemDoesntexistException {
		if (!this.repo.existsById(id)) {
			throw new ItemDoesntexistException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

	public boolean deleteItem(String name) throws ItemDoesntexistException {
		return this.deleteItem(this.repo.findByItemName(name).getId());
	}

	
	public Items findItemByID(Long id, Long freezerId) throws ItemDoesntexistException {
		
		Optional<Items> item = repo.findAll().stream().filter(items -> items.getId() == id && items.getFreezer_id() == freezerId).findFirst();
		
		return item.orElseThrow( 
				() -> new ItemDoesntexistException());
	} 
	


	
	public Items updateItem(Items item, Long itemId, Long freezerId) throws ItemDoesntexistException, FreezerDoesntexistException {
		Items toUpdate = findItemByID(itemId, freezerId);
		toUpdate.setItemName(item.getItemName());
		toUpdate.setQuantity(item.getQuantity());
		return this.repo.save(toUpdate);
	}

	
	public Items updateItem(Items item, String name, Long fId) throws ItemDoesntexistException, FreezerDoesntexistException {
		return this.updateItem(item, this.repo.findByItemName(name).getId(),fId); 

	}
}
