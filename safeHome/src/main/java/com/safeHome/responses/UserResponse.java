package com.safeHome.responses;


public class UserResponse<T> {
    private boolean status;
    private String message;
    private T result;

    public UserResponse(boolean status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return result;
    }

    public void setData(T result) {
        this.result = result;
    }
}
