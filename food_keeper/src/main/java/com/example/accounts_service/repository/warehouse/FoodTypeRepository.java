package com.example.accounts_service.repository.warehouse;

import com.example.accounts_service.models.food_warehouse.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
}
