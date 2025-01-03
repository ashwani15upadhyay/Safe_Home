package com.safeHome.responses;

import com.safeHome.model.User;

public class VerificationResponse {
    private boolean success;
    private String message;
    private String isRegistered;
    private User result;  // This should be of type User, not a list

    // Constructor
    public VerificationResponse(boolean success, String message, String isRegistered, User result) {
        this.success = success;
        this.message = message;
        this.isRegistered = isRegistered;
        this.result = result;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(String isRegistered) {
        this.isRegistered = isRegistered;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
