package com.gsmh.kz.home.model.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone() {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
