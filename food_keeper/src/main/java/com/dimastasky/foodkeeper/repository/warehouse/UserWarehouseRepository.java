package com.dimastasky.foodkeeper.repository.warehouse;

import com.dimastasky.foodkeeper.models.warehouse.UserWarehouse;
import com.dimastasky.foodkeeper.models.warehouse.UserWarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWarehouseRepository extends JpaRepository<UserWarehouse, UserWarehouseId> {
}
