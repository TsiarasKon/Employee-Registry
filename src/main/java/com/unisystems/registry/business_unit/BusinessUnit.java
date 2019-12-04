package com.unisystems.registry.business_unit;

import com.unisystems.registry.company.Company;

import javax.persistence.*;

@Entity
public class BusinessUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Company company;

    public BusinessUnit() {
    }

    public BusinessUnit(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public BusinessUnit(String name) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
