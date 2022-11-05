package com.dimastasky.foodkeeper.models.food_warehouse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserWarehouse {
    @EmbeddedId
    private UserWarehouseId id;

    public UserWarehouse(Long userId, Long warehouseId) {
        this.id = new UserWarehouseId(userId, warehouseId);
    }

}
