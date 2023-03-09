package com.dimastasky.foodkeeper.models.medicine_warehouse;

import com.dimastasky.foodkeeper.models.enums.EMedicineFormType;
import com.dimastasky.foodkeeper.models.food_warehouse.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicine_form_type")
@Getter
@Setter
@NoArgsConstructor
public class MedicineFormType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private EMedicineFormType formType;

    // todo: Название формы лекарства на русском
    private String ruName;

    private String engName;
    // todo: Сокращенное название метрики
    private String metric;

    @JsonIgnore
    @OneToMany(mappedBy = "formType")
    private Set<Medicine> medicines;

    public MedicineFormType(EMedicineFormType medicineFormType) {
        this.formType = medicineFormType;
    }
}
