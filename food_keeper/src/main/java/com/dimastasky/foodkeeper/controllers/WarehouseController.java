package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dtos.warehouse_dto.WarehouseDTO;
import com.dimastasky.foodkeeper.models.dtos.user_dto.UserIdDTO;
import com.dimastasky.foodkeeper.models.warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.dtos.warehouse_dto.WarehouseCreationDTO;
import com.dimastasky.foodkeeper.models.warehouse.WarehouseType;
import com.dimastasky.foodkeeper.services.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private final WarehouseService service;

    @GetMapping("/all-warehouses")
    public List<Warehouse> getAllWarehouses() {
        return service.findAllWarehouses();
    }

    @PostMapping("/current-user-warehouses")
    public List<Warehouse> getUserWarehouses(@RequestBody UserIdDTO user) {
        return service.findWarehousesByUserId(user.getUserId());
    }

    @GetMapping("warehouse-types")
    public List<WarehouseType> getAllWarehouseTypes() {
        return service.finAllWarehouseTypes();
    }

    // todo: Создать ограничение на максимальное к-во складов для пользователя
    @PostMapping("/warehouse")
    public WarehouseCreationDTO createWarehouse(@Valid @RequestBody WarehouseCreationDTO warehouseCreationDTO) {
        return service.createWarehouse(warehouseCreationDTO);
    }

//    @GetMapping("/warehouse")
//    public Warehouse getWarehouse(@RequestParam("id") Long id) {
//        return service.getWarehouseById(id);
//    }

    @PutMapping("/warehouse")
    public WarehouseDTO updateWarehouse(WarehouseDTO warehouseDTO) {
        return service.updateWarehouseById(warehouseDTO);
    }

//    @DeleteMapping("/warehouse")
//    public ResponseEntity<?> deleteWarehouse(@RequestParam Long warehouseId,
//                                             @RequestParam Long userId) {
//        return service.deleteWarehouseById(warehouseId, userId);
//    }

}
