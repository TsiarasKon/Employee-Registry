package com.unisystems.registry.employee;

public class EmployeeResponse {
    private long id;
    private long recordNumber;
    private String fullName;
    private String telephone;
    private String workingPeriod;
    private String contractType;
    private String position;
    private String status;
    private String unitName;

    public EmployeeResponse(long id, long recordNumber, String fullName, String telephone, String workingPeriod, String contractType, String position, String status, String unitName) {
        this.id = id;
        this.recordNumber = recordNumber;
        this.fullName = fullName;
        this.telephone = telephone;
        this.workingPeriod = workingPeriod;
        this.contractType = contractType;
        this.position = position;
        this.status = status;
        this.unitName = unitName;
    }

    public long getId() {
        return id;
    }

    public long getRecordNumber() {
        return recordNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getWorkingPeriod() {
        return workingPeriod;
    }

    public String getContractType() {
        return contractType;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRecordNumber(long recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setWorkingPeriod(String workingPeriod) {
        this.workingPeriod = workingPeriod;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
