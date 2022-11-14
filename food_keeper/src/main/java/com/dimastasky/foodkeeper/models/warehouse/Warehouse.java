package com.dimastasky.foodkeeper.models.warehouse;

import com.dimastasky.foodkeeper.models.food_warehouse.FoodRecords;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "warehouse")
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "warehouse_type_id")
    private WarehouseType warehouseType;


    @JsonIgnore
    @OneToMany(mappedBy = "warehouse")
    private Set<FoodRecords> foodRecords;


    public Warehouse() {
    }

    public Warehouse(String name, WarehouseType warehouseType) {
        this.name = name;
        this.warehouseType = warehouseType;
    }
}
