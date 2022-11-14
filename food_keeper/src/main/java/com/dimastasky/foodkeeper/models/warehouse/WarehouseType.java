package com.dimastasky.foodkeeper.models.warehouse;

import com.dimastasky.foodkeeper.models.enums.EWarehouseType;
import com.dimastasky.foodkeeper.models.warehouse.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "warehouse_type")
@Getter
@Setter
public class WarehouseType {
    //todo: Продумать параметры для температурного режима и влажности склада
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EWarehouseType name;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouseType")
    private Set<Warehouse> warehouse;

}
