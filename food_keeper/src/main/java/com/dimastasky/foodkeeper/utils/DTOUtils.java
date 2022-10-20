package com.dimastasky.foodkeeper.utils;

import com.dimastasky.foodkeeper.models.dtos.DTOEntity;
import org.modelmapper.ModelMapper;

public class DTOUtils {
    public DTOEntity convertToDto(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public Object convertToEntity(Object obj, DTOEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }
}
