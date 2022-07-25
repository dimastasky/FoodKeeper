package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
}
