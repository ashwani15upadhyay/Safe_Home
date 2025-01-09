package com.safeHome.controller;

import com.safeHome.exception.ResourceNotFoundExp;
import com.safeHome.model.OtpRequest;
import com.safeHome.model.UpdateModel;
import com.safeHome.responses.UserResponse;
import com.safeHome.model.EmployeeIdRequest;
import com.safeHome.responses.OtpResponse;
import com.safeHome.model.User;
import com.safeHome.responses.VerificationResponse;
import com.safeHome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse<User>> userCreate(@Valid @RequestBody User user) {
        UserResponse<User> apiResponse;

        try {
            if (userService.existsByEmployeeId(user.getEmployeeId())) {
                apiResponse = new UserResponse<>(false, "User already exists", null);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
            }
            User createdUser = userService.saveUser(user);
            apiResponse = new UserResponse<>(true, "User created successfully", createdUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (ResourceNotFoundExp exp) {
            apiResponse = new UserResponse<>(false, exp.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            apiResponse = new UserResponse<>(false, "An unexpected error occurred", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<OtpResponse> sendOtp(@RequestBody @Valid EmployeeIdRequest employeeIdRequest) {
        OtpResponse otpResponse;
        try {
            String otp = userService.sendOtp(employeeIdRequest.getEmployeeId());
            Map<String, String> data = new HashMap<>();
            data.put("otp", otp);
            otpResponse = new OtpResponse(true, "OTP sent successfully", data);
            return ResponseEntity.status(HttpStatus.OK).body(otpResponse);
        } catch (ResourceNotFoundExp exp) {
            otpResponse = new OtpResponse(false, exp.getMessage(), new ArrayList<>());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(otpResponse);
        } catch (Exception e) {
            otpResponse = new OtpResponse(false, "An unexpected error occurred", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(otpResponse);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<VerificationResponse> verifyOtp(@RequestBody @Valid OtpRequest otpRequest) {
        VerificationResponse response;
        User user = userService.user(otpRequest.getEmployeeId());
        String isRegistered = String.valueOf(user.getIsRegistered());
        System.out.println("isRegustered : " + isRegistered);
        try {

            boolean isValid = userService.verifyOtp(otpRequest.getEmployeeId(), otpRequest.getOtp());

            if (isValid) {

                if (user != null) {
                    if (user.getIsRegistered() == 1 || user.getIsRegistered() == 0) {

                        response = new VerificationResponse(true, "OTP verified successfully", isRegistered, user
                        );
                        return ResponseEntity.status(HttpStatus.OK).body(response);
                    } else {
                        response = new VerificationResponse(true, "OTP verified successfully", isRegistered, null
                        );
                        return ResponseEntity.status(HttpStatus.OK).body(response);
                    }
                } else {
                    response = new VerificationResponse(false, "User not found.", isRegistered, null
                    );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            } else {
                response = new VerificationResponse(false, "Invalid or expired OTP", isRegistered, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (ResourceNotFoundExp exp) {
            response = new VerificationResponse(false, exp.getMessage(), isRegistered, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response = new VerificationResponse(false, "An unexpected error occurred", isRegistered, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> getAllUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(getAllUsers);
    }

    @GetMapping("/users/{employeeId}")
    public ResponseEntity<User> getUser(@PathVariable String employeeId) {
        User user = userService.user(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{employeeId}/update")
    public ResponseEntity<UserResponse<User>> updateLocation(@PathVariable String employeeId, @RequestBody UpdateModel updateModel) {
        try {
            User updatedUser = userService.updateLocation(employeeId, updateModel);
            UserResponse<User> userResponse = new UserResponse<>(true, "User profile updated successfully", updatedUser);
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } catch (ResourceNotFoundExp e) {
            return ResponseEntity.notFound().build();
        }
    }
}

