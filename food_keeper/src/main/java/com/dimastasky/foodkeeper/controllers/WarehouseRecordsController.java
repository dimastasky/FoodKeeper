package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dtos.WarehouseRecordsDTO.RecordCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.WarehouseRecordsDTO.RecordsDto;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseRecords;
import com.dimastasky.foodkeeper.services.WarehouseRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse/records")
@AllArgsConstructor
public class WarehouseRecordsController{

    private final WarehouseRecordsService service;

    @PostMapping("/get-records")
    public List<WarehouseRecords> getWarehouseRecords(@RequestBody RecordsDto dto) {
        return service.findAllRecords(dto.getWarehouseId(), dto.getUserId());
    }

    @PostMapping("/record")
    public RecordCreationDTO addProductToW(@Valid @RequestBody RecordCreationDTO recordCreationDTO) {
        return service.addRecordToWarehouse(recordCreationDTO);
    }
}
