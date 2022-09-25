package com.gsmh.kz.home.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String phone;

    @NotBlank
    private String code;


    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
