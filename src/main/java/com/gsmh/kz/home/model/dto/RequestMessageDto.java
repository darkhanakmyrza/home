package com.gsmh.kz.home.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMessageDto {

    private String text;
    private Long toUserId;
    private Long adsId;
}
