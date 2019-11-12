package com.unisystems.registry.department;

import com.unisystems.registry.business_unit.BusinessUnit;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private BusinessUnit bu;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BusinessUnit getBu() {
        return bu;
    }

    public void setBu(BusinessUnit bu) {
        this.bu = bu;
    }
}
