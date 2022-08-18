package com.dimastasky.foodkeeper.models.dto.WarehouseRecordsDTO;

import com.dimastasky.foodkeeper.models.dto.DTOEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class RecordCreationDTO implements DTOEntity {
    @NotNull
    private Long user;

    @NotNull
    private Long product;

    @NotNull
    private Integer quantity;

    private Date bestBefore;

}
