package com.stock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.DemoStockManagementSystemPocApplication;
import com.stock.entity.Items;
import com.stock.entity.Warehouse;
import com.stock.service.WarehouseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

	//private static final Logger LOGGER=LoggerFactory.getLogger(WarehouseController.class);
	

	private final WarehouseService warehouseService;
	
	public WarehouseController(WarehouseService warehouseService) {
		super();
		this.warehouseService = warehouseService;
	}

//Add warehouse	
	@PostMapping("/add_warehouse")
	public ResponseEntity<Warehouse> addwarehouse(@RequestBody Warehouse warehouse){
		
		List<Items> items = new ArrayList<>();
		items =warehouse.getItems();
		warehouse.setItems(null);
		Warehouse warehouse2 = warehouseService.addWarehouse(warehouse);
		
		for(Items items2: items) {
			items2.setWarehouse(warehouse2);
		}
		
		warehouse2.setItems(items);
		log.info("Add warehouse");
		return ResponseEntity.ok(warehouseService.addWarehouse(warehouse2));
	}

//Get details of warehouse using warehouse_id
	@GetMapping("/get_details_of_warehouse/{warehouse_id}")
	public ResponseEntity<Warehouse> getByWarehouseId(@PathVariable("warehouse_id") int id) {
		return ResponseEntity.ok(warehouseService.findById(id));
	}
	
//Get list of warehouse	
	@GetMapping("get_list_of_warehouse")
	public ResponseEntity<List<Warehouse>> getAllWarehouse(){
		return ResponseEntity.ok(warehouseService.getAllWarehouse());
	}
	
//Delete warehouse by id
	@DeleteMapping("delete_warehouse_by_id/{warehouse_id}")
	public ResponseEntity<String> deleteWarehouseByid(@PathVariable("warehouse_id") int id){
		return ResponseEntity.ok(warehouseService.deleteWarehouseById(id));
	}
	
	
	
	
	
	
	
	
	
	
}
