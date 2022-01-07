package com.stock.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stock.entity.Items;
import com.stock.entity.Warehouse;
import com.stock.exception.IdNotFoundException;
import com.stock.exception.WarehouseNotFoundexception;
import com.stock.repository.WarehouseRepository;

@Service
@Transactional
public class WarehouseService {
	
	private final WarehouseRepository warehouseRepository;
	
	public WarehouseService(WarehouseRepository warehouseRepository) {
		super();
		this.warehouseRepository = warehouseRepository;
	}

	public Warehouse addWarehouse(Warehouse warehouse) {
		
		return warehouseRepository.save(warehouse);
	}
	
	
	public Warehouse findById(int id)  {
		
		if(id<=0) {
			throw new RuntimeException("Other type of exception");
		}	
		
		Warehouse warehouse = warehouseRepository.findByWarehouseId(id);
		if(warehouse == null) {
			throw new IdNotFoundException("Invalid warehouse id "+id);
		}
	//	return warehouseRepository.findByWarehouseId(id);
		return warehouse;
	}
	
	public List<Warehouse> getAllWarehouse() {
				
		List<Warehouse> warehouse = warehouseRepository.findAll();
		if(warehouse.isEmpty()) {
			throw new WarehouseNotFoundexception("Warehouse not found");
		}
		return warehouse;
	}
	
	public String deleteWarehouseById(int id) {
		warehouseRepository.deleteById(id);
		return "Warehouse successfully delted";
	}


	
}
