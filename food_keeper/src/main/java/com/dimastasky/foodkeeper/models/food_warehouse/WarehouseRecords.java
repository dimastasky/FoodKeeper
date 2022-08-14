package com.dimastasky.foodkeeper.models.food_warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Integer count;

    @Temporal(TemporalType.DATE)
    private Date bestBefore;

    //TODO: Timestamp дата последнего добавления на склад


}
