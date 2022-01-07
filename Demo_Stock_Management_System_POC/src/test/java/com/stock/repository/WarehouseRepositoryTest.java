package com.stock.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.entity.Items;
import com.stock.entity.Warehouse;

@SpringBootTest
//@DataJpaTest
class WarehouseRepositoryTest {

	@Autowired
	private WarehouseRepository warehouseRepository;
	
//	@AfterEach
//	void tearDown() {
//		warehouseRepository.deleteAll();
//	}
	
	
	//@Test
	void testFindAll() {
		//Warehouse warehouse = new Warehouse();
		List<Warehouse> warehouses = warehouseRepository.findAll();
		assertNotNull(warehouses);
		assertEquals(2, warehouses.size());
	}
	
	
	@Test
	void testFindByWarehouseId() {
		//given
		//int id = 1;
		Warehouse warehouse = new Warehouse();
		warehouseRepository.save(warehouse);
		//when
		boolean expected = warehouseRepository.existsById(1);
		//then
		assertThat(expected).isTrue();
	}
	
	
	@Test
	public void testSaveWarehouse() {
		Warehouse warehouse = new Warehouse();
		Items items = new Items();
		items.setCurrentStock(30);
		List<Items> items2 = new ArrayList<Items>();
		items2.add(items);
		warehouseRepository.save(warehouse);
		//Assertions.assertThat(warehouse.getWarehouseId()).isGreaterThan(0);
		Assert.assertNotNull(warehouse.getWarehouseId());
		
	}


}
