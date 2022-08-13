package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRecordsRepository extends JpaRepository<WarehouseRecords, Long> {
}
