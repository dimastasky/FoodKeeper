package com.dimastasky.foodkeeper.models.dtos.food_records_dto;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FoodRecordCreationDTO implements DTOEntity {
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
