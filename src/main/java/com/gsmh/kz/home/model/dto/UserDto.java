package com.gsmh.kz.home.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String avatarUrl;
    private String email;
    private String phone;
    private String city;
    private String company;
    private String code;


}
