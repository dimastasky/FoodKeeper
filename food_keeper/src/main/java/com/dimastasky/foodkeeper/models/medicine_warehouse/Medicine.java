package com.dimastasky.foodkeeper.models.medicine_warehouse;

import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormatSymbols;

@Entity
@Table(name = "medicine")
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "med_form_type_id", referencedColumnName = "id")
    private MedicineFormType formType;

    @ManyToOne
    @JoinColumn(name = "med_type_id", referencedColumnName = "id")
    private MedicineType medicineType;

    private Float dosage;

    private Integer pack;

    private Integer servePerPackage;




}


