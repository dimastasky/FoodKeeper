package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineRecords;
import com.dimastasky.foodkeeper.services.MedicineRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse/med-records")
@AllArgsConstructor
public class MedicineRecordsController {

    private final MedicineRecordsService service;

    @GetMapping("/all-records")
    @Cacheable("med-records")
    public List<MedicineRecords> getAllMedicineRecords(@RequestParam("warehouseId") Long warehouseId) {
        return service.findAllRecords(warehouseId);
    }

}
