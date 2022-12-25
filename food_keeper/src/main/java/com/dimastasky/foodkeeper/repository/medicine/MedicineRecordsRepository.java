package com.dimastasky.foodkeeper.repository.medicine;

import com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedNativeQueries;
import java.util.List;

public interface MedicineRecordsRepository extends JpaRepository<MedicineRecords, Long> {

    //@Query(value = "select mr from com.dimastasky.foodkeeper.models.medicine_warehouse.MedicineRecords mr where mr.warehouse = :warehouseId", nativeQuery = true)
    //List<MedicineRecords> findAllByWarehouseId(@Param("warehouseId") Long warehouseId);

    List<MedicineRecords> findAllByWarehouseId(Long warehouseId);


}
