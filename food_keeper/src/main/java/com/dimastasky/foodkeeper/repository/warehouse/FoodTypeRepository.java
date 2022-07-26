package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.data.EFoodType;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
    Optional<FoodType> findByName(EFoodType name);
}
