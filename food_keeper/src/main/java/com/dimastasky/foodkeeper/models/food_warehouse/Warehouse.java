package com.dimastasky.foodkeeper.models.food_warehouse;

import com.dimastasky.foodkeeper.models.account.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "warehouse_type_id")
    private WarehouseType warehouseType;


    @JsonIgnore
    @OneToMany(mappedBy = "warehouse")
    private Set<WarehouseRecords> warehouseRecords;


    public Warehouse() {
    }

    public Warehouse(String name, WarehouseType warehouseType) {
        this.name = name;
        this.warehouseType = warehouseType;
    }
}
