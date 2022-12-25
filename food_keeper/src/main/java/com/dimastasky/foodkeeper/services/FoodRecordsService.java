package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.dtos.product_records_dto.ProductRecCreationDTO;
import com.dimastasky.foodkeeper.models.warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodRecords;
import com.dimastasky.foodkeeper.repository.user.UserRepository;
import com.dimastasky.foodkeeper.repository.food.ProductRepository;
import com.dimastasky.foodkeeper.repository.food.FoodRecordsRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodRecordsService {

    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;
    private final FoodRecordsRepository foodRecordsRepository;
    private final ProductRepository productRepository;

    public List<FoodRecords> findAllRecords(Long warehouseId) {
        List<FoodRecords> foodRecords = new ArrayList<>();
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);

        // todo: Оптимизировать запрос, отфильтровать
        for (FoodRecords record : foodRecordsRepository.findAll()) {
            if (warehouse.getFoodRecords().contains(record)) {
                foodRecords.add(record);
            }
        }

        if (foodRecords.isEmpty()) {
            return null;
        } else {
            return foodRecords;
        }
    }

    public ProductRecCreationDTO addRecordToWarehouse(ProductRecCreationDTO productRecord) {
        FoodRecords foodRecords = new FoodRecords();
        Warehouse warehouse = warehouseRepository.getReferenceById(productRecord.getWarehouseId());
        User currentUser = userRepository.getReferenceById(productRecord.getUserId());

        //todo: Обновление записи, если productId и Срок годности совпадают
//        if (warehouse.getOwners().contains(currentUser)) {
            foodRecords.setWarehouse(warehouse);
            foodRecords.setProduct(productRepository.getReferenceById(productRecord.getProductId()));
            foodRecords.setCount(productRecord.getQuantity());
            foodRecords.setBestBefore(productRecord.getBestBefore());

            foodRecordsRepository.save(foodRecords);
            return productRecord;

    }




    // todo: edit
    // todo: delete
    // todo: add one, take one product

}