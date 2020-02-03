package com.bae.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bae.persistence.domain.Freezers;
import com.bae.persistence.domain.Items;
import com.bae.persistence.repo.FreezerRepo;
import com.bae.persistence.repo.ItemsRepo;

@Service
public class FreezerService {

	private FreezerRepo repo;
	private ItemsRepo itemRepo;

	private ItemService itemService;

	public FreezerService(FreezerRepo repo, ItemService itemService, ItemsRepo itemRepo) {
		super();
		this.repo = repo;
		this.itemService = itemService;
		this.itemRepo = itemRepo;
	}

	public List<Freezers> readFreezers() {
		return this.repo.findAll();
	}

	public Freezers createFreezer(Freezers freezer) {
		return this.repo.save(freezer);
	}

	public Freezers findFreezerByID(Long id) throws FreezerDoesntexistException {
		return this.repo.findById(id).orElseThrow(() -> new FreezerDoesntexistException());
	}


	public boolean  deleteFreezer(Long id) throws FreezerDoesntexistException, ItemDoesntexistException {
		if(!this.repo.existsById(id)) {
			throw new FreezerDoesntexistException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}


	public boolean  deleteFreezer(String name) throws FreezerDoesntexistException, ItemDoesntexistException {
		return this.deleteFreezer(this.repo.findByFreezerName(name).getId()); 
	}

	public Freezers addItemToFreezer(Long id, Items item) throws FreezerDoesntexistException {
		Freezers toUpdate = findFreezerByID(id);
		Items newItem = this.itemService.createItem(item);
		toUpdate.getItems().add(newItem);
		return this.repo.saveAndFlush(toUpdate);
	}

	public Set<Items> getItemsFromFreezer(Long id) throws FreezerDoesntexistException {
		Freezers toDisplay = findFreezerByID(id);
		return toDisplay.getItems();
	}

	public boolean deleteItemFromFreezer(String name, Long id)
			throws ItemDoesntexistException, FreezerDoesntexistException, ItemIsNotInFreezerException {
		Freezers currentFreezer = this.findFreezerByID(id);
		Set<Items> itemSet = currentFreezer.getItems();
		boolean found = false;
		for (Items items : itemSet) {
			if (items.getItemName().equals(name)) {
				found = true;
			}
		}
		if (found == true) {

			currentFreezer.getItems().remove((this.itemRepo.findByItemName(name)));
			this.itemService.deleteItem(this.itemRepo.findByItemName(name).getId());

			return true; 
		}
		else {

			throw new ItemIsNotInFreezerException();
		}

	}

}
