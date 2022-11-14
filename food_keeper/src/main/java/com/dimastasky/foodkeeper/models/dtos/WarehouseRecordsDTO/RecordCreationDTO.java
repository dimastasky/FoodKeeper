package com.dimastasky.foodkeeper.models.dtos.WarehouseRecordsDTO;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class RecordCreationDTO implements DTOEntity {
    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Integer quantity;

    private LocalDate bestBefore;

}
