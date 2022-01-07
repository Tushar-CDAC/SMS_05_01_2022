package com.stock.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.entity.Items;
import com.stock.entity.Warehouse;
import com.stock.exception.IdNotFoundException;
import com.stock.exception.WarehouseNotFoundexception;
import com.stock.repository.ItemsRepository;
import com.stock.repository.WarehouseRepository;

@Service
@Transactional
public class ItemsService {

	private final ItemsRepository itemsRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;

	public ItemsService(ItemsRepository itemsRepository) {
		super();
		this.itemsRepository = itemsRepository;
	}

	public Items findByWarehouseIdAndItemId(Warehouse warehouseId, int itemId) {
		
		Items items = itemsRepository.findByWarehouseAndItemId(warehouseId, itemId);
		if (items == null) {
			throw new IdNotFoundException("Invalid item_id "+itemId);
		}
		
		return items;

	}
	
	public String deleteItemsById(int id) {
		itemsRepository.deleteById(id);
		return "Items successfully deleted";
	}
}
