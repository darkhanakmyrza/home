package com.gsmh.kz.home.model.dto;

import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdsStatusDto {
    private Long id;
    private String moderateMsg;
    private AdModeratorStatusEnum status;
}
