package com.bae.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.persistence.domain.Items;
import com.bae.service.ItemService;
import com.bae.service.FreezerDoesntexistException;
import com.bae.service.ItemDoesntexistException;

@RestController
public class ItemsController {

	private ItemService service;

	@Autowired
	public ItemsController(ItemService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createItems")
	public Items createItems(@RequestBody Items items) {
		return this.service.createItem(items);
	}

	@DeleteMapping("/deleteItem/{id}")
	public void deleteItem(@PathVariable Long id) throws ItemDoesntexistException {
		this.service.deleteItem(id);
	}


	
	@PutMapping("/updateItem/{id}/{fId}")
	public Items updateItem(@PathVariable Long id, @PathVariable Long fId, @RequestBody Items item) throws ItemDoesntexistException, FreezerDoesntexistException  {
		return this.service.updateItem(item, id, fId);
	}
	@PutMapping("/updateItemByName/{name}/{fId}")
	public Items updateItem(@PathVariable("name") String name, @PathVariable("fId") Long fId, @RequestBody Items item) throws ItemDoesntexistException, FreezerDoesntexistException {
		return this.service.updateItem(item, name, fId);

	}

}