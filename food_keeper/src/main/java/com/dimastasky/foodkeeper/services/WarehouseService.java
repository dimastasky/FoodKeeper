package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.dtos.WarehouseDTO.WarehouseCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.WarehouseDTO.WarehouseDTO;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseType;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService {

    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseTypeRepository warehouseTypeRepository;


    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<Warehouse> findWarehousesByUserId(Long userID) {
        System.out.println("UserId "+ userID);
        List<Warehouse> warehouses = new ArrayList<>();
        User currentUser = userRepository.getReferenceById(userID);

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

    public List<WarehouseType> finAllWarehouseTypes() {
        return warehouseTypeRepository.findAll();
    }

    public WarehouseCreationDTO createWarehouse(WarehouseCreationDTO warehouseCreationDTO) {
        Warehouse warehouse = new Warehouse();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) authentication.getPrincipal();
//        long userId = currentUser.getId();



        warehouse.setName(warehouseCreationDTO.getName());
        warehouse.setWarehouseType(warehouseTypeRepository.getReferenceById(warehouseCreationDTO.getWarehouseType()));
        warehouseRepository.save(warehouse);

        return warehouseCreationDTO;
    }

    public Warehouse getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.getReferenceById(id);
        User currentUser = userRepository.getReferenceById(id);

        if(warehouse.getOwners().contains(currentUser)) {
            return warehouseRepository.getReferenceById(id);
        } else {
            return null;
        }
    }

    public WarehouseDTO updateWarehouseById(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseDTO.getId());

        warehouse.setName(warehouseDTO.getName());
        warehouse.setWarehouseType(warehouseTypeRepository.getReferenceById(warehouseDTO.getWarehouseType()));

        warehouseRepository.save(warehouse);
        return warehouseDTO;
    }

    public ResponseEntity<?> deleteWarehouseById(Long warehouseId, Long userId) {
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);
        User currentUser = userRepository.getReferenceById(userId);

        if (warehouse.getOwners().contains(currentUser)) {
            warehouseRepository.deleteById(warehouseId);
            return new ResponseEntity<>("Warehouse with id " + warehouseId + " deleted.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Not your warehouse", HttpStatus.UNAUTHORIZED);
        }
    }





}
