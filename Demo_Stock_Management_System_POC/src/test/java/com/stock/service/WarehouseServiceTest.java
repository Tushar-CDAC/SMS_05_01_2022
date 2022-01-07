package com.stock.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.stock.entity.Items;
import com.stock.entity.Warehouse;
import com.stock.repository.WarehouseRepository;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {

	@Mock 
	private WarehouseRepository warehouseRepository;
	//private AutoCloseable autoCloseable;
	
	private WarehouseService warehouseService;
	
	@BeforeEach
	void setUp() {
		//autoCloseable = MockitoAnnotations.openMocks(this);
		warehouseService = new WarehouseService(warehouseRepository);
	}
	
//	@AfterEach
//	void tearDown() throws Exception{
//		autoCloseable.close();
//	}
//	
	
	@Test
	void testGetAllWarehouse() {
		//when
		warehouseService.getAllWarehouse();
		//then
		verify(warehouseRepository).findAll();
		
	}
	
	@Test
	void testAddWarehouse() {
		Warehouse warehouse = new Warehouse();
		//warehouse.setWarehouseId(1);
		Items items = new Items();
		items.setCurrentStock(10);
		//items.setItemId(2);
		List<Items> items2 = new ArrayList<Items>();
		items2.add(items);
		warehouseRepository.save(warehouse);
		Assert.assertNotNull(warehouse.getWarehouseId());
		//Assertions.assertThat(warehouse.getWarehouseId()).isGreaterThan(0);
	}
	
	@Test
	void testFindById() {
		Warehouse warehouse = new Warehouse();
		warehouseRepository.save(warehouse);
		Assertions.assertThat(warehouse.getWarehouseId()).isEqualTo(1);
	}

	

}
