package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.enums.EFoodType;
import com.dimastasky.foodkeeper.models.warehouse.WarehouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseTypeRepository extends JpaRepository<WarehouseType, Integer> {
    Optional<WarehouseType> findByName(EFoodType name);
}
