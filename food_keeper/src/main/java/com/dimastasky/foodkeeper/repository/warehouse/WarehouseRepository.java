package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
