package com.dimastasky.foodkeeper.controllers;

import com.dimastasky.foodkeeper.models.dtos.medicine_dto.MedicineCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.medicine_dto.MedicineDTO;
import com.dimastasky.foodkeeper.models.medicine_warehouse.Medicine;
import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineFormType;
import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineType;
import com.dimastasky.foodkeeper.services.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse/medicine")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService service;

    @GetMapping("/medicines")
    @Cacheable("medicine")
    public List<Medicine> findAllMedicines() {
        return service.findAllMedicines();
    }

    @GetMapping("/medicine")
    @Cacheable("medicine")
    public MedicineDTO findMedicineById(@RequestParam Long id) {
        return service.findMedicineById(id);
    }

    @PostMapping("/medicine")
    @CacheEvict(value = "medicine", allEntries = true)
    public MedicineCreationDTO addMedicine(@RequestBody MedicineCreationDTO medicineDTO) {
        return service.addMedicine(medicineDTO);
    }

    @PutMapping("/medicine")
    @CacheEvict(value = "medicine", allEntries = true)
    public MedicineDTO editMedicine(@RequestBody MedicineDTO medicineDTO) {
        return service.editMedicine(medicineDTO);
    }

    @DeleteMapping("/medicine")
    @CacheEvict(value = "medicine", allEntries = true)
    public ResponseEntity<?> deleteMedicine(@RequestParam Long id) {
        service.deleteMedicineById(id);
        return new ResponseEntity<>("Medicine with id " + id + " deleted.", HttpStatus.ACCEPTED);
    }

    @GetMapping("/medicine-types")
    @Cacheable("medicine-types")
    public List<MedicineType> findAllMedTypes() {
        return service.findAllMedTypes();
    }

    @GetMapping("/medicine-form-types")
    @Cacheable("medicine-form-types")
    public List<MedicineFormType> findAllMedFormTypes() {
        return service.findAllMedFormTypes();
    }

}
