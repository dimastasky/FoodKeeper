package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineRecords;
import com.dimastasky.foodkeeper.models.warehouse.Warehouse;
import com.dimastasky.foodkeeper.repository.medicine.MedicineRecordsRepository;
import com.dimastasky.foodkeeper.repository.medicine.MedicineRepository;
import com.dimastasky.foodkeeper.repository.user.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicineRecordsService {

    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;
    private final MedicineRecordsRepository medRecordsRepository;
    private final MedicineRepository medicineRepository;

    public List<MedicineRecords> findAllRecords(Long warehouseId) {
        List<MedicineRecords> medicineRecords = new ArrayList<>();
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);
        //todo: Оптимизировать запрос,
        return medRecordsRepository.findAllByWarehouseId(warehouseId);
    }




}
