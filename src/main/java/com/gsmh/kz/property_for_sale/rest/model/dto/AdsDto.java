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
    private int constructionYear;
    private int price;
    private Long userId;
    private Boolean isPledged;
    private String balcony;
    private Boolean isBalconyGlazed;
    private String furniture;
    private String ceilingHeight;
    private String safety;
    private Boolean exchange;
    private Long payment;
    private Long rassrochkaPrice;
    private String propertyType;
    private String region;
    private String city;

}
