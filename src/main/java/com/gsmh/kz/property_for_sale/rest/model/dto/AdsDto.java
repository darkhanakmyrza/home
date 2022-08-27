package com.gsmh.kz.property_for_sale.rest.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class AdsDto {
    private Long id;
    private String description;
    private int roomsCount;
    private int houseNumber;
    private int floor;
    private int floorsCount;
    private int creationYear;
    private int price;
    private Long userId;
}
