package com.example.accounts_service.repository.warehouse;

import com.example.accounts_service.models.food_warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
