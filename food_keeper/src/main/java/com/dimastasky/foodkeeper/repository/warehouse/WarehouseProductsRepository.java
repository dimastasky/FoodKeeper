package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseProductsRepository extends JpaRepository<WarehouseProducts, Long> {
}
