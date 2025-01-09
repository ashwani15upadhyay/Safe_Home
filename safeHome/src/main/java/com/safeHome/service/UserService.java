package com.safeHome.service;

import com.safeHome.exception.ResourceNotFoundExp;
import com.safeHome.model.UpdateModel;
import com.safeHome.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    String sendOtp(String employeeId) throws ResourceNotFoundExp;

    boolean verifyOtp(String employeeId, String otp) throws ResourceNotFoundExp;

    List<User> getAllUsers();

    User user(String employeeId) throws ResourceNotFoundExp;

    User updateLocation(String employeeId, UpdateModel updateModel) throws ResourceNotFoundExp;

    boolean existsByEmployeeId(String employeeId) throws  ResourceNotFoundExp;



}
