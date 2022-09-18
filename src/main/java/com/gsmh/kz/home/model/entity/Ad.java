package com.gsmh.kz.home.model.entity;

import com.gsmh.kz.home.array.IntArrayType;
import com.gsmh.kz.home.array.StringArrayType;
import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "ads")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@TypeDefs({
    @TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
    ),
    @TypeDef(
        name = "int-array",
        typeClass = IntArrayType.class
    )
})
public class Ad extends Audit {
  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "adsIdSeq", sequenceName = "ads_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adsIdSeq")
  private Long id;

  @Column(name = "description")
  private String description;

  @Column(name = "rooms_count")
  private int roomsCount;

  @Column(name = "house_number")
  private int houseNumber;

  @Column(name = "floor")
  private int floor;

  @Column(name = "floors_count")
  private int floorsCount;

  @Column(name = "creation_year")
  private int creationYear;

  @Column(name = "price")
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

  @Type(type = "string-array")
  @Column(name = "photos", columnDefinition = "text[]")
  private String[] photos;

  @Enumerated(EnumType.STRING)
  private AdModeratorStatusEnum moderatorStatus;

  public Ad(String description, int roomsCount, int houseNumber, int floor, int floorsCount, int creationYear, int price,
            Boolean isPledged, String balcony, Boolean isBalconyGlazed,
            String furniture, String ceilingHeight, String safety, Boolean exchange, Long payment, Long rassrochkaPrice,
            String propertyType, String region, String city, AdModeratorStatusEnum moderatorStatus, String[] photos) {
    this.description = description;
    this.roomsCount = roomsCount;
    this.houseNumber = houseNumber;
    this.floor = floor;
    this.floorsCount = floorsCount;
    this.creationYear = creationYear;
    this.price = price;
    this.isPledged = isPledged;
    this.balcony = balcony;
    this.isBalconyGlazed = isBalconyGlazed;
    this.furniture = furniture;
    this.ceilingHeight = ceilingHeight;
    this.safety = safety;
    this.exchange = exchange;
    this.payment = payment;
    this.rassrochkaPrice = rassrochkaPrice;
    this.propertyType = propertyType;
    this.region = region;
    this.city = city;
    this.moderatorStatus = moderatorStatus;
    this.photos = photos;
  }
}
