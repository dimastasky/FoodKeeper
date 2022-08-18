package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseRecords;
import com.dimastasky.foodkeeper.models.dto.WarehouseRecordsDTO.RecordCreationDTO;
import com.dimastasky.foodkeeper.models.dto.WarehouseDTO.WarehouseCreationDTO;
import com.dimastasky.foodkeeper.models.dto.userDTO.UserIdDTO;
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
    ProductTypeRepository productTypeRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    WarehouseRecordsRepository warehouseRecordsRepository;

    @Autowired
    WarehouseTypeRepository warehouseTypeRepository;

    // TODO: Задать права доступа к методам

    // TODO:

    //  склады всех пользователей (для админа)
    @GetMapping("/all-warehouses")
    public List<Warehouse> getAllWarehouses() { return warehouseRepository.findAll(); }

    // Склады текущего пользователя
    @PostMapping("/all-user-warehouses")
    public List<Warehouse> getAllUserWarehouses(@RequestBody UserIdDTO userRequest) {
        List<Warehouse> warehouses = new ArrayList<>();
        User currentUser = userRepository.getReferenceById(userRequest.getId());
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
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id,
                                             @RequestBody UserIdDTO userRequest) {
        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        User currentUser = userRepository.getReferenceById(userRequest.getId());

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
    public ResponseEntity<?> createWarehouse(@Valid @RequestBody WarehouseCreationDTO warehouseCreationDTO) {
        Warehouse warehouse = new Warehouse();

        warehouse.setName(warehouseCreationDTO.getName());
        warehouse.setWarehouseType(warehouseTypeRepository.getReferenceById(warehouseCreationDTO.getWarehouseType()));

        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse created.");
    }

    // Получить все записи выбранного склада
    @PostMapping("/warehouse/{id}/records")
    public List<WarehouseRecords> getWarehouseRecords(@RequestBody UserIdDTO userRequest,
                                                      @PathVariable Long id) {
        List<WarehouseRecords> warehouseRecords = new ArrayList<>();
        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        User currentUser = userRepository.getReferenceById(userRequest.getId());

        // todo: Оптимизировать запрос, отфильтровать
        for (WarehouseRecords record : warehouseRecordsRepository.findAll()) {
            if (warehouse.getOwners().contains(currentUser)) {
                warehouseRecords.add(record);
            }
        }
        if (warehouseRecords.isEmpty()) {
            return null;
        } else {
            return warehouseRecords;
        }
    }

    // Добавить продукт на склад
    @PostMapping("/warehouse/{id}/add_record")
    public ResponseEntity<?> addProductToW(@Valid @RequestBody RecordCreationDTO recordCreationDTO,
                                           @PathVariable Long id) {
        WarehouseRecords warehouseRecords = new WarehouseRecords();

        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        //todo: user ID ?
        User currentUser = userRepository.getReferenceById(recordCreationDTO.getUser());



        //todo: обновление записи, если Продукт и срок годности совпадают
        if (warehouse.getOwners().contains(currentUser)) {
            warehouseRecords.setWarehouse(warehouseRepository.getReferenceById(id));
            warehouseRecords.setProduct(productRepository.getReferenceById(recordCreationDTO.getProduct()));
            warehouseRecords.setCount(recordCreationDTO.getQuantity());
            warehouseRecords.setBestBefore(recordCreationDTO.getBestBefore());


            warehouseRecordsRepository.save(warehouseRecords);

            return ResponseEntity.ok("Product added.");
        } else {
            return new ResponseEntity<>("Product add to warehouse is Unauthorized.", HttpStatus.UNAUTHORIZED);
        }
    }





}
