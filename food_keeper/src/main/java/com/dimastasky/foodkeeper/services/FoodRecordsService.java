package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.account.User;
import com.dimastasky.foodkeeper.models.dtos.food_records_dto.FoodRecordCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.food_records_dto.FoodRecordDTO;
import com.dimastasky.foodkeeper.models.warehouse.Warehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.FoodRecord;
import com.dimastasky.foodkeeper.repository.user.UserRepository;
import com.dimastasky.foodkeeper.repository.food.ProductRepository;
import com.dimastasky.foodkeeper.repository.food.FoodRecordsRepository;
import com.dimastasky.foodkeeper.repository.warehouse.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodRecordsService {

    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;
    private final FoodRecordsRepository foodRecordsRepository;
    private final ProductRepository productRepository;

    public List<FoodRecord> findAllRecords(Long warehouseId) {
        List<FoodRecord> foodRecords = new ArrayList<>();
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId);

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        // todo: Оптимизировать запрос, отфильтровать
        return foodRecordsRepository.findAllByWarehouseId(warehouseId);
    }

    public FoodRecordCreationDTO addFoodRecord(FoodRecordCreationDTO params) {
        FoodRecord foodRecord = new FoodRecord();
        Warehouse warehouse = warehouseRepository.getReferenceById(params.getWarehouseId());
        User currentUser = userRepository.getReferenceById(params.getUserId());

        //todo: Обновление записи, если productId и Срок годности совпадают
//        if (warehouse.getOwners().contains(currentUser)) {
            foodRecord.setWarehouse(warehouse);
            foodRecord.setProduct(productRepository.getReferenceById(params.getProductId()));
            foodRecord.setCount(params.getQuantity());
            foodRecord.setBestBefore(params.getBestBefore());

            foodRecordsRepository.save(foodRecord);
            return params;
    }

    public FoodRecordDTO editFoodRecord(FoodRecordDTO params) {
        FoodRecord record = new FoodRecord();

        return params;
    }




    // todo: edit
    // todo: delete
    // todo: add one, take one product

}