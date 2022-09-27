package com.gsmh.kz.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdsDto {
  private Long id;
  private String description;
  private int roomsCount;
  private int houseNumber;
  private int floor;
  private int floorsCount;
  private int constructionYear;
  private int price;
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
  private String[] photos;
  private Boolean isFavAds;
  private String moderateMsg;
}
