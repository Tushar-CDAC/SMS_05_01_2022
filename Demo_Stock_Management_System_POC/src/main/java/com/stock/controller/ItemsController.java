package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.entity.Items;
import com.stock.entity.Warehouse;
import com.stock.exception.LessStockException;
import com.stock.model.ItemModule;
import com.stock.repository.ItemsRepository;
import com.stock.repository.WarehouseRepository;
import com.stock.service.ItemsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stock")
@Slf4j
public class ItemsController {
	
//	private static final Logger LOGGER=LoggerFactory.getLogger(ItemsController.class);
	
	private final ItemsService itemsService;
	
	private final ItemsRepository itemsRepository;
	
	private final WarehouseRepository warehouseRepository;
	
	public ItemsController(ItemsService itemsService, ItemsRepository itemsRepository,
			WarehouseRepository warehouseRepository) {
		super();
		this.itemsService = itemsService;
		this.itemsRepository = itemsRepository;
		this.warehouseRepository = warehouseRepository;
	}

//Get Stock details by Warehouse_id and Item_id

	@GetMapping("get_stock_details/by/{warehouse_id}/{item_id}")
	public ResponseEntity<Integer> getStockDetails(@PathVariable("warehouse_id") Warehouse id2, @PathVariable("item_id") int id1) {	
	Items items=itemsService.findByWarehouseIdAndItemId(id2, id1);
		//LOGGER.info("Current stock");
		log.info("Current stock");
		return ResponseEntity.ok(items.getCurrentStock());
	}
		
	
//	@PostMapping("order/{warehouse_id}/{item_id}/{quantity}")
//	public ResponseEntity<Integer> orderQty(@PathVariable("warehouse_id") Warehouse id2, @PathVariable("item_id") int id1, @PathVariable("quantity") int quantity) {
//		Items items=itemsService.findByWarehouseIdAndName(id2, id1);
//		int newQty = items.getCurrentStock() - quantity;
//		items.setCurrentStock(newQty);
//		itemsRepository.save(items);
//		//LOGGER.info("Order quantity");
//		log.info("Order quantity");
//		return ResponseEntity.ok(items.getCurrentStock());		
//	}
	
	
	//Reduce quantity
	@PostMapping("order/by")
	public ResponseEntity<Integer> orderQty(@RequestBody ItemModule itemModule) {
		Warehouse warehouse = warehouseRepository.findByWarehouseId(itemModule.getWarehouseId());
		Items items=itemsService.findByWarehouseIdAndItemId(warehouse, itemModule.getItemId());
		int newQty = items.getCurrentStock() - itemModule.getReduceQuantity();
		if(items.getCurrentStock()<itemModule.getReduceQuantity()) 
			throw new LessStockException("Available stock is less than required");
		items.setCurrentStock(newQty);
		itemsRepository.save(items);
		//LOGGER.info("Order quantity");
		log.info("Order quantity");
		return ResponseEntity.ok(items.getCurrentStock());		
	}

	//Delete item by id
	@DeleteMapping("delete_items_by_id/{items_id}")
	public ResponseEntity<String> deleteItemsById(@PathVariable("items_id") int id){
		return ResponseEntity.ok(itemsService.deleteItemsById(id));
	}
	
}
