package com.dimastasky.foodkeeper.models.food_warehouse;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "warehouse_records")
@Getter
@Setter
public class WarehouseRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Integer count;

    private Double weightKg;

    private Date bestBefore;

    //TODO: Timestamp дата последнего добавления на склад


}
