package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.enums.EFoodType;
import com.dimastasky.foodkeeper.models.food_warehouse.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    Optional<ProductType> findByName(EFoodType name);
}
