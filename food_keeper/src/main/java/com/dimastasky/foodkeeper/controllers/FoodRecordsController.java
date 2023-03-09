package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dtos.food_records_dto.FoodRecordCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.food_records_dto.ProductRecordsDto;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodRecord;
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
    public List<FoodRecord> getWarehouseRecords(@RequestBody ProductRecordsDto dto) {
        return service.findAllRecords(dto.getWarehouseId());
    }

    @PostMapping("/record")
    @CacheEvict(value = "records", allEntries = true)
    public FoodRecordCreationDTO addProductToW(@Valid @RequestBody FoodRecordCreationDTO productRecord) {
        return service.addFoodRecord(productRecord);
    }

    // todo: edit
    // todo: delete
    // todo: add one, take one product
}
