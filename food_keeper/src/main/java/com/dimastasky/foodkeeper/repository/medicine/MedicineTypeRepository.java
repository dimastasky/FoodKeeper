package com.dimastasky.foodkeeper.repository.medicine;

import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineTypeRepository extends JpaRepository<MedicineType, Integer> {
}
