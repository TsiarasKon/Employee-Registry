package com.unisystems.registry.employee;

import com.unisystems.registry.task.Task;
import com.unisystems.registry.unit.Unit;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Employee {
    private static long initialRecordNum = 111500001;     // just an arbitrary convention for record numbers

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long recordNum;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    // we'd use Calendar instead of LocalDate (or the deprecated Date) if dealing with global employees
    private LocalDate recruitmentDate;
    private LocalDate exitDate;
    private boolean status;     // false: inactive, true: active
    private EmployeeContractType employeeContractType;
    private EmployeePosition employeePosition;
    @ManyToOne
    private Unit unit;

    @ManyToMany
    private List<Task> task;


    public Employee() {
    }

    public Employee(String firstName, String lastName, String phoneNumber, LocalDate recruitmentDate, EmployeeContractType employeeContractType, EmployeePosition employeePosition) {
        // constructor used for active employees
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.recruitmentDate = recruitmentDate;
        this.employeeContractType = employeeContractType;
        this.employeePosition = employeePosition;
        this.status = true;
        this.recordNum = generateRecordNum();
    }

    public Employee(String firstName, String lastName, String address, String phoneNumber, LocalDate recruitmentDate, LocalDate exitDate, boolean status, EmployeeContractType employeeContractType, EmployeePosition employeePosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.recruitmentDate = recruitmentDate;
        this.exitDate = exitDate;
        this.status = status;
        this.employeeContractType = employeeContractType;
        this.employeePosition = employeePosition;
        this.recordNum = generateRecordNum();
    }

    private long generateRecordNum() {
        return initialRecordNum++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(long recordNum) {
        this.recordNum = recordNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(LocalDate recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public EmployeeContractType getEmployeeContractType() {
        return employeeContractType;
    }

    public void setEmployeeContractType(EmployeeContractType employeeContractType) {
        this.employeeContractType = employeeContractType;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}