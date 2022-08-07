package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseRecords;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.ProductsRecordRequest;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.WarehouseRequest;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.WarehouseUserRequest;
import com.dimastasky.foodkeeper.repository.RoleRepository;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.*;
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

    @Autowired
    WarehouseProductsRepository warehouseProductsRepository;

    @Autowired
    WarehouseTypeRepository warehouseTypeRepository;

    // TODO: Задать права доступа к методам

    @GetMapping("/all-warehouses")
    public List<Warehouse> getAllWarehouses() { return warehouseRepository.findAll(); }

    @GetMapping("/warehouse/{id}")
    public Warehouse getWarehouse(@PathVariable Long id)
    {
        return warehouseRepository.getReferenceById(id);
    }

    @DeleteMapping("/warehouse/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id ,@RequestBody WarehouseUserRequest userRequest) {
        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        User currentUser = userRepository.getReferenceById(id);

        if (warehouse.getOwners().contains(currentUser)) {
            warehouseRepository.deleteById(id);
        }
        return new ResponseEntity<>("Warehouse with id " + id + " deleted.", HttpStatus.ACCEPTED);
    }

    // Todo: Создать ограничение на максимальное к-во складов для пользователя

    //-----Создать склад-----
    @PostMapping("/warehouse")
    public ResponseEntity<?> createWarehouse(@Valid @RequestBody WarehouseRequest warehouseRequest) {
        Warehouse warehouse = new Warehouse();

        warehouse.setName(warehouseRequest.getName());
        warehouse.setWarehouseType(warehouseTypeRepository.getReferenceById(warehouseRequest.getWarehouseType()));

        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse created.");
    }

    @PostMapping("/warehouse/newRecord")
    public ResponseEntity<?> addProductToW(@Valid @PathVariable Long productId, @RequestBody ProductsRecordRequest productsCount) {
        WarehouseRecords warehouseRecords = new WarehouseRecords();

        warehouseRecords.setCount(productsCount.getCount());

        warehouseProductsRepository.save(warehouseRecords);

        return ResponseEntity.ok("Product added.");
    }





}
