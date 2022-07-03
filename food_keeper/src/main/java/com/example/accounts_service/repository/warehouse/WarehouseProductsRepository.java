package com.example.accounts_service.repository.warehouse;

import com.example.accounts_service.models.food_warehouse.WarehouseProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseProductsRepository extends JpaRepository<WarehouseProducts, Long> {
}
