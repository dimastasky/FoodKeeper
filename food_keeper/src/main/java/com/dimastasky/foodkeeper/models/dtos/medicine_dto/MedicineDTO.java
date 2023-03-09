package com.dimastasky.foodkeeper.models.dtos.medicine_dto;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDTO implements DTOEntity {
    private Long id;
    private String name;
    private Integer medFormTypeId;
    private Integer medTypeId;
    private Float dosage;
    private Integer pack;
    private Integer servePerPack;
}
