package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dtos.WarehouseRecordsDTO.RecordCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.WarehouseRecordsDTO.RecordsDto;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodRecords;
import com.dimastasky.foodkeeper.services.FoodRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse/records")
@AllArgsConstructor
public class FoodRecordsController {

    private final FoodRecordsService service;

    @PostMapping("/get-records")
    @Cacheable("records")
    public List<FoodRecords> getWarehouseRecords(@RequestBody RecordsDto dto) {
        return service.findAllRecords(dto.getWarehouseId());
    }

    @PostMapping("/record")
    @CacheEvict(value = "records", allEntries = true)
    public RecordCreationDTO addProductToW(@Valid @RequestBody RecordCreationDTO recordCreationDTO) {
        return service.addRecordToWarehouse(recordCreationDTO);
    }

    // todo: edit
    // todo: delete
    // todo: add one, take one product
}
