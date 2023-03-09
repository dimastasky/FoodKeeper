package com.dimastasky.foodkeeper.models.medicine_warehouse;

import com.dimastasky.foodkeeper.models.enums.EMedicineType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicine_type")
@Getter
@Setter
@NoArgsConstructor
public class MedicineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private EMedicineType medicineType;

    private String ruName;

    private String engName;

    @JsonIgnore
    @OneToMany(mappedBy = "medicineType")
    private Set<Medicine> medicines;

    public MedicineType(EMedicineType medicineType) {
        this.medicineType = medicineType;
    }
}
