package com.safeHome.model;

import jakarta.validation.constraints.NotNull;

public class EmployeeIdRequest {

    @NotNull(message = "Employee ID is required")
    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
