package com.dimastasky.foodkeeper.repository.food;

import com.dimastasky.foodkeeper.models.food_warehouse.FoodRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRecordsRepository extends JpaRepository<FoodRecord, Long> {

    List<FoodRecord> findAllByWarehouseId(Long warehouseId);
}
