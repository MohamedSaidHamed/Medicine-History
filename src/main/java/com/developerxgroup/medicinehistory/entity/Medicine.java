/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developerxgroup.medicinehistory.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Mohamed.Hamed
 */
@Entity
@Table(name = "Medicine")
public class Medicine implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "medName")
    private String medName;
    @Column(name = "effictiveMaterial")
    private String effictiveMaterial;
    @Column(name = "referenceNum")
    private String referenceNum;
    @Column(name = "active")
    private Boolean active;

    public Medicine() {
    }

    public Medicine(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getEffictiveMaterial() {
        return effictiveMaterial;
    }

    public void setEffictiveMaterial(String effictiveMaterial) {
        this.effictiveMaterial = effictiveMaterial;
    }

    public String getReferenceNum() {
        return referenceNum;
    }

    public void setReferenceNum(String referenceNum) {
        this.referenceNum = referenceNum;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

   
}
