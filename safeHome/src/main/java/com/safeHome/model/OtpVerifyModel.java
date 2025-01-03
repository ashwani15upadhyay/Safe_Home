package com.safeHome.model;

import jakarta.validation.constraints.NotBlank;

public class OtpVerifyModel {

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    public OtpVerifyModel(String employeeId, String otp) {
        this.employeeId = employeeId;
        this.otp = otp;
    }

    public OtpVerifyModel() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @NotBlank(message = "OTP is required")
    private String otp;
}
