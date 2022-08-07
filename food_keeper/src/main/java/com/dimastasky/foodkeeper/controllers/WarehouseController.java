package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseRecords;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.WarehouseRecordRequest;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.WarehouseRequest;
import com.dimastasky.foodkeeper.payload.request.foodkeeper.CurrentUserRequest;
import com.dimastasky.foodkeeper.repository.RoleRepository;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @PostMapping("/all-user-warehouses")
    public List<Warehouse> getAllUserWarehouses(@RequestBody CurrentUserRequest userRequest) {
        List<Warehouse> warehouses = new ArrayList<>();
        User currentUser = userRepository.getReferenceById(userRequest.getUser());
        for (Warehouse warehouse : warehouseRepository.findAll()) {
            if (warehouse.getOwners().contains(currentUser)) {
                warehouses.add(warehouse);
            }
        }
        if (warehouses.size() > 0) {
            return warehouses;
        } else {
            return null;
        }
    }

    @GetMapping("/warehouse/{id}")
    public Warehouse getWarehouse(@PathVariable Long id)
    {
        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        User currentUser = userRepository.getReferenceById(id);
        if(warehouse.getOwners().contains(currentUser)) {
            return warehouseRepository.getReferenceById(id);
        } else {
            return null;
        }
    }

    @DeleteMapping("/warehouse/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id ,@RequestBody CurrentUserRequest userRequest) {
        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        User currentUser = userRepository.getReferenceById(id);

        if (warehouse.getOwners().contains(currentUser)) {
            warehouseRepository.deleteById(id);
            return new ResponseEntity<>("Warehouse with id " + id + " deleted.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Not your warehouse", HttpStatus.UNAUTHORIZED);
        }

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

    @PostMapping("/warehouse/{id}/record")
    public ResponseEntity<?> addProductToW(@Valid @RequestBody WarehouseRecordRequest warehouseRecordRequest, @PathVariable Long id) {
        WarehouseRecords warehouseRecords = new WarehouseRecords();

        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        User currentUser = userRepository.getReferenceById(id);

        //todo: обновление записи, если Продукт и срок годности совпадают
        if (warehouse.getOwners().contains(currentUser)) {
            warehouseRecords.setWarehouse(warehouseRepository.getReferenceById(id));
            warehouseRecords.setProduct(productRepository.getReferenceById(warehouseRecordRequest.getProduct()));
            warehouseRecords.setCount(warehouseRecordRequest.getCount());
            warehouseRecords.setBestBefore(warehouseRecordRequest.getBestBefore());
            warehouseRecords.setWeightKg(warehouseRecordRequest.getWeightKg());

            warehouseProductsRepository.save(warehouseRecords);

            return ResponseEntity.ok("Product added.");
        } else {
            return new ResponseEntity<>("Product add Unauthorized.", HttpStatus.UNAUTHORIZED);
        }
    }






}
