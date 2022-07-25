package com.dimastasky.foodkeeper.models.food_warehouse;

import com.dimastasky.foodkeeper.models.data.EWarehouseType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EWarehouseType warehouseType;

    public Warehouse() {
    }

    public Warehouse(String name, EWarehouseType warehouseType) {
        this.name = name;
        this.warehouseType = warehouseType;
    }
}
