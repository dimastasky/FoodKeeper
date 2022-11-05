package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.dtos.WarehouseDTO.WarehouseCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.WarehouseDTO.WarehouseDTO;
import com.dimastasky.foodkeeper.models.dtos.userDTO.UserIdDTO;
import com.dimastasky.foodkeeper.models.food_warehouse.UserWarehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseType;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.UserWarehouseRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService {

    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseTypeRepository warehouseTypeRepository;

    private final UserWarehouseRepository userWarehouseRepository;


    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<Warehouse> findWarehousesByUserId(Long userId) {
        List<Warehouse> warehouses = new ArrayList<>();
        User currentUser = userRepository.getReferenceById(userId);


        for (UserWarehouse userWarehouse : userWarehouseRepository.findAll()) {
            if (userWarehouse.getId().getUserId().equals(userId)) {
                Warehouse warehouse = warehouseRepository.getReferenceById(userWarehouse.getId().getWarehouseId());
                warehouses.add(warehouse);
                System.out.println("warehouse : " + warehouseRepository.getReferenceById(userWarehouse.getId().getWarehouseId()));
                System.out.println("name: " + warehouseRepository.getReferenceById(userWarehouse.getId().getWarehouseId()).getName());
                System.out.println("id: " + userWarehouse.getId().getWarehouseId());
            }
        }

        if (warehouses.size() > 0) {
            return warehouses;
        } else {
            return null;
        }
    }

    public List<WarehouseType> finAllWarehouseTypes() {
        return warehouseTypeRepository.findAll();
    }

    @Autowired
    AuthenticationManager authenticationManager;

    public WarehouseCreationDTO createWarehouse(WarehouseCreationDTO warehouseCreationDTO, UserIdDTO user) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseCreationDTO.getName());
        warehouse.setWarehouseType(warehouseTypeRepository.getReferenceById(warehouseCreationDTO.getWarehouseType()));
        warehouseRepository.save(warehouse);

        return warehouseCreationDTO;
    }

//    public Warehouse getWarehouseById(Long id) {
//        Warehouse warehouse = warehouseRepository.getReferenceById(id);
//        User currentUser = userRepository.getReferenceById(id);
//
//        if(warehouse.getOwners().contains(currentUser)) {
//            return warehouseRepository.getReferenceById(id);
//        } else {
//            return null;
//        }
//    }

    public WarehouseDTO updateWarehouseById(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseDTO.getId());

        warehouse.setName(warehouseDTO.getName());
        warehouse.setWarehouseType(warehouseTypeRepository.getReferenceById(warehouseDTO.getWarehouseType()));

        warehouseRepository.save(warehouse);
        return warehouseDTO;
    }

//    public ResponseEntity<?> deleteWarehouseById(Long warehouseId, Long userId) {
//        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);
//        User currentUser = userRepository.getReferenceById(userId);
//
//        if (warehouse.getOwners().contains(currentUser)) {
//            warehouseRepository.deleteById(warehouseId);
//            return new ResponseEntity<>("Warehouse with id " + warehouseId + " deleted.", HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<>("Not your warehouse", HttpStatus.UNAUTHORIZED);
//        }
//    }





}
