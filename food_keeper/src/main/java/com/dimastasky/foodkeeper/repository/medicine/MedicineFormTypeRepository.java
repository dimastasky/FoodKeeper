package com.dimastasky.foodkeeper.repository.medicine;

import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineFormType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineFormTypeRepository extends JpaRepository<MedicineFormType, Integer> {
}
