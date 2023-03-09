package com.dimastasky.foodkeeper.models.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class UserWarehouseId implements Serializable {
    private Long userId;
    private Long warehouseId;

    public UserWarehouseId(Long userId, Long warehouseId) {
        this.userId = userId;
        this.warehouseId = warehouseId;
    }

}
