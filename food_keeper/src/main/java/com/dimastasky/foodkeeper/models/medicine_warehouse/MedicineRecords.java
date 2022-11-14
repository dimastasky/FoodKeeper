package com.dimastasky.foodkeeper.models.medicine_warehouse;

import com.dimastasky.foodkeeper.models.warehouse.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicine_records")
@Getter
@Setter
public class MedicineRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    private Medicine medicine;

    private Integer count;

    @Temporal(TemporalType.DATE)
    private LocalDate expirationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate timestamp;
}
