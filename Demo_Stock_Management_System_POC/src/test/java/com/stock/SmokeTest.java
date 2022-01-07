package com.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.controller.ItemsController;
import com.stock.controller.WarehouseController;

@SpringBootTest
public class SmokeTest {
	
	@Autowired
	private ItemsController itemsController;
	
	@Autowired
	private WarehouseController warehouseController;
	
	@Test
	public void contextLoads() throws Exception{
		 Assertions.assertThat(itemsController).isNotNull();
	}
	
	@Test
	public void contextLoads2() throws Exception{
		Assertions.assertThat(warehouseController).isNotNull();
	}
}
