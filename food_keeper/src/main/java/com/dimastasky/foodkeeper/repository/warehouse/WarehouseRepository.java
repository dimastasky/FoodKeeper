package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
