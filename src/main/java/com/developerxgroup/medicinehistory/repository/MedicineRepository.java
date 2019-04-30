/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developerxgroup.medicinehistory.repository;

import com.developerxgroup.medicinehistory.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohamed.Hamed
 */
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer>{
    
}
