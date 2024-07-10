package com.example.fleet.response;

public class ApiResponse {
    private boolean ok;
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ApiResponse(boolean ok, String message, String status) {
        this.ok = ok;
        this.message = message;
        this.status = status;
    }
}
