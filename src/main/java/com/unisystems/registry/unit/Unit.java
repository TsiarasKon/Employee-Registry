package com.unisystems.registry.unit;

import com.unisystems.registry.department.Department;

import javax.persistence.*;

@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Department dept;

    public Unit() {
    }

    public Unit(long id, String name, Department dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }

    public Unit(String name) {
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

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
