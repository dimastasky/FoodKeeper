package com.dimastasky.foodkeeper.repository.medicine;

import com.dimastasky.foodkeeper.models.medicine_warehouse.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
