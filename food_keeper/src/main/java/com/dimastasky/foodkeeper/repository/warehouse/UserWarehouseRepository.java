package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.food_warehouse.UserWarehouse;
import com.dimastasky.foodkeeper.models.food_warehouse.UserWarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWarehouseRepository extends JpaRepository<UserWarehouse, UserWarehouseId> {

    //todo: find by user id
//    @Query()
//    public UserWarehouse findByUserId(Long userId);

    //todo: find by warehouse id
}
