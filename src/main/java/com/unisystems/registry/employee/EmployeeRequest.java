package com.unisystems.registry.employee;

import com.unisystems.registry.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String recruitmentDate;
    private String exitDate;
    private String status;
    private String employeeContractType;
    private String employeePosition;
    private long unitId;

    private SimpleDateFormat sdf;
    private LocalDate recruitmentDateValid;
    private LocalDate exitDateValid;
    private boolean statusValid;
    private EmployeeContractType employeeContractTypeValid;
    private EmployeePosition employeePositionValid;

    public EmployeeRequest() {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
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

    public String getRecruitmentDate() {
        return recruitmentDate;
    }

    public void setRecruitmentDate(String recruitmentDate) {
        this.recruitmentDate = recruitmentDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeContractType() {
        return employeeContractType;
    }

    public void setEmployeeContractType(String employeeContractType) {
        this.employeeContractType = employeeContractType;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public LocalDate getRecruitmentDateValid() {
        return recruitmentDateValid;
    }

    public LocalDate getExitDateValid() {
        return exitDateValid;
    }

    public boolean isStatusValid() {
        return statusValid;
    }

    public EmployeeContractType getEmployeeContractTypeValid() {
        return employeeContractTypeValid;
    }

    public EmployeePosition getEmployeePositionValid() {
        return employeePositionValid;
    }

    public ResponseEntity<Object> validateRequest() {
        if (firstName == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide firstName"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (lastName == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide lastName"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (status == null || (!status.equalsIgnoreCase("active") && !status.equalsIgnoreCase("inactive"))) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide status as 'active' or 'inactive'"),
                    HttpStatus.BAD_REQUEST
            );
        }
        this.statusValid = status.equalsIgnoreCase("active");

        try {
            sdf.parse(recruitmentDate);
        } catch (ParseException e) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide recruitmentDate like 'yyyy-MM-dd'"),
                    HttpStatus.BAD_REQUEST
            );
        }
        this.recruitmentDateValid = LocalDate.parse(recruitmentDate);

        if (exitDate != null) {
            try {
                sdf.parse(exitDate);
            } catch (ParseException e) {
                return new ResponseEntity<>(
                        new GenericError(2, "Invalid request body", "Please provide exitDate like 'yyyy-MM-dd'"),
                        HttpStatus.BAD_REQUEST
                );
            }
            this.exitDateValid = LocalDate.parse(exitDate);
        }

        if (employeeContractType != null) {
            try {
                this.employeeContractTypeValid = EmployeeContractType.valueOf(employeeContractType);
            } catch (IllegalArgumentException | NullPointerException  e) {
                return new ResponseEntity<>(
                        new GenericError(2, "Invalid request body", "Please provide a valid employeeContractType"),
                        HttpStatus.BAD_REQUEST
                );
            }
        }

        if (employeePosition != null) {
            try {
                this.employeePositionValid = EmployeePosition.valueOf(employeePosition);
            } catch (IllegalArgumentException | NullPointerException  e) {
                return new ResponseEntity<>(
                        new GenericError(2, "Invalid request body", "Please provide a valid employeePosition"),
                        HttpStatus.BAD_REQUEST
                );
            }
        }

        if (unitId <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide unitId"),
                    HttpStatus.BAD_REQUEST
            );
        }

        return null;
    }
}
