package com.dimastasky.foodkeeper.services;

import com.dimastasky.foodkeeper.models.dtos.medicine_dto.MedicineCreationDTO;
import com.dimastasky.foodkeeper.models.dtos.medicine_dto.MedicineDTO;
import com.dimastasky.foodkeeper.models.medicine_warehouse.Medicine;
import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineFormType;
import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineType;
import com.dimastasky.foodkeeper.repository.medicine.MedicineFormTypeRepository;
import com.dimastasky.foodkeeper.repository.medicine.MedicineRepository;
import com.dimastasky.foodkeeper.repository.medicine.MedicineTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medRepository;
    private final MedicineTypeRepository medTypeRepository;
    private final MedicineFormTypeRepository medFormTypeRepository;

    ModelMapper modelMapper = new ModelMapper();

    public MedicineDTO findMedicineById(Long id) {
        Medicine medicine = medRepository.getReferenceById(id);
        return modelMapper.map(medicine, MedicineDTO.class);
    }

    public List<Medicine> findAllMedicines() {
        return medRepository.findAll();
    }

    public MedicineCreationDTO addMedicine(MedicineCreationDTO medicineDTO) {
        Medicine medicine = new Medicine();

        medicine.setName(medicineDTO.getName());
        medicine.setMedicineType(medTypeRepository.getReferenceById(medicineDTO.getMedTypeId()));
        medicine.setFormType(medFormTypeRepository.getReferenceById(medicineDTO.getMedFormTypeId()));
        medicine.setDosage(medicineDTO.getDosage());
        medicine.setPack(medicineDTO.getPack());
        medicine.setServePerPackage(medicineDTO.getServePerPack());

        medRepository.save(medicine);
        return medicineDTO;
    }

    public MedicineDTO editMedicine(MedicineDTO medicineDTO) {
        Medicine medicine = medRepository.getReferenceById(medicineDTO.getId());

        medicine.setName(medicineDTO.getName());
        medicine.setMedicineType(medTypeRepository.getReferenceById(medicineDTO.getMedTypeId()));
        medicine.setFormType(medFormTypeRepository.getReferenceById(medicineDTO.getMedFormTypeId()));
        medicine.setDosage(medicineDTO.getDosage());
        medicine.setPack(medicineDTO.getPack());
        medicine.setServePerPackage(medicineDTO.getServePerPack());

        medRepository.save(medicine);
        return medicineDTO;
    }

    public void deleteMedicineById(Long id) {
        medRepository.deleteById(id);
    }

    //todo: перенести в отдельный CRUD сервис
    public List<MedicineType> findAllMedTypes() {
        return medTypeRepository.findAll();
    }

    //todo: перенести в отдельный CRUD сервис
    public List<MedicineFormType> findAllMedFormTypes() {
        return medFormTypeRepository.findAll();
    }

}
