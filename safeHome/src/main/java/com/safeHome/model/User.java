package com.safeHome.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = "employeeId")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee ID is required")
    @Column(nullable = false, unique = true, length = 20)
    private String employeeId;

    @NotNull(message = "Employee Name is required")
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "Email is required")
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotNull(message = "Mobile number is required")
    @Column(nullable = false, unique = true, length = 20)
    private String mobile;

    @NotNull(message = "Address is required")
    @Column(nullable = false, length = 100)
    private String address;

    @NotNull(message = "Gender is required")
    @Column(nullable = false, length = 10)
    private String gender;

    @Column(name = "otp", length = 10)
    private String otp;

    @Column(name = "otp_expiry")
    private LocalDateTime otpExpiry;

    public String getOtp() {
        return otp;
    }

    private int isRegistered;
    private String longitude;
    private String latitude;

    public int getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(int isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
