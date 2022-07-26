package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.WarehouseRequest;
import com.dimastasky.foodkeeper.repository.RoleRepository;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.FoodTypeRepository;
import com.dimastasky.foodkeeper.repository.warehouse.ProductRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FoodTypeRepository foodTypeRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    // TODO: Задать права доступа к методам

    @GetMapping("/all-warehouses")
    public List<Warehouse> getAllWarehouses() { return warehouseRepository.findAll(); }

    @GetMapping("/warehouse/{id}")
    public Warehouse getWarehouse(@PathVariable Long id)
    {
        return warehouseRepository.getReferenceById(id);
    }

    @DeleteMapping("/warehouse/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id) {
        warehouseRepository.deleteById(id);
        return new ResponseEntity<>("Warehouse with id " + id + " deleted.", HttpStatus.ACCEPTED);
    }

    // Todo: Создать ограничение на максимальное к-во складов для пользователя
    @PostMapping("/warehouse")
    public ResponseEntity<?> createWarehouse(@Valid @RequestBody WarehouseRequest warehouseRequest) {
        Warehouse warehouse = new Warehouse();

        warehouse.setName(warehouseRequest.getName());
        warehouse.setWarehouseType(warehouseRequest.getWarehouseType());

        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse created.");
    }


}
