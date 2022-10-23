package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.dtos.WarehouseRecordsDTO.RecordCreationDTO;
import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.WarehouseRecords;
import com.dimastasky.foodkeeper.repository.UserRepository;
import com.dimastasky.foodkeeper.repository.warehouse.ProductRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRecordsRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseRecordsService {

    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;
    private final WarehouseRecordsRepository warehouseRecordsRepository;
    private final ProductRepository productRepository;

    public List<WarehouseRecords> findAllRecords(Long warehouseId, Long userId) {
        List<WarehouseRecords> warehouseRecords = new ArrayList<>();
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);

        // todo: Оптимизировать запрос, отфильтровать
        for (WarehouseRecords record : warehouseRecordsRepository.findAll()) {
            if (warehouse.getWarehouseRecords().contains(record)) {
                warehouseRecords.add(record);
            }
        }

        if (warehouseRecords.isEmpty()) {
            return null;
        } else {
            return warehouseRecords;
        }
    }

    public RecordCreationDTO addRecordToWarehouse(RecordCreationDTO recordCreationDTO) {
        WarehouseRecords warehouseRecords = new WarehouseRecords();
        Warehouse warehouse = warehouseRepository.getReferenceById(recordCreationDTO.getWarehouseId());
        User currentUser = userRepository.getReferenceById(recordCreationDTO.getUserId());

        //todo: Обновление записи, если productId и Срок годности совпадают
        if (warehouse.getOwners().contains(currentUser)) {
            warehouseRecords.setWarehouse(warehouse);
            warehouseRecords.setProduct(productRepository.getReferenceById(recordCreationDTO.getProductId()));
            warehouseRecords.setCount(recordCreationDTO.getQuantity());
            warehouseRecords.setBestBefore(recordCreationDTO.getBestBefore());

            warehouseRecordsRepository.save(warehouseRecords);
            return recordCreationDTO;
        } else {
            return null;
        }

    }

}