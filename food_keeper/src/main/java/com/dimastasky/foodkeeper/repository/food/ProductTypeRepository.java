package com.dimastasky.foodkeeper.repository.food;

import com.dimastasky.foodkeeper.models.enums.EFoodType;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<FoodType, Integer> {
    //Optional<FoodType> findByName(EFoodType name);
}
