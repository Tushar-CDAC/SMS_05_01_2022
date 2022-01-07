package com.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.entity.Items;
import com.stock.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
	
	public Warehouse save(Warehouse warehouse);
	
	public Warehouse findByWarehouseId(int id);
	
	public List<Warehouse> findAll();
	
}
