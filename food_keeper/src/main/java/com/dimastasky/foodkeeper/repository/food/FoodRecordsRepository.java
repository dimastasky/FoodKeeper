package com.dimastasky.foodkeeper.repository.food;

import com.dimastasky.foodkeeper.models.food_warehouse.FoodRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRecordsRepository extends JpaRepository<FoodRecords, Long> {
}
