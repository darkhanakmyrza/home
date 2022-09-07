package com.gsmh.kz.home.model.entity;

import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ads")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Ad {
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

  @CreatedDate
  @Column(nullable = false)
  private Date createdDate;

  @JsonIgnore
  @LastModifiedDate
  @Column(nullable = false)
  private Date updatedDate;

  @JsonIgnore
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

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

  private AdModeratorStatusEnum moderatorStatus;

  public Ad(String description, int roomsCount, int houseNumber, int floor, int floorsCount, int creationYear, int price,
            User user, Boolean isPledged, String balcony, Boolean isBalconyGlazed,
            String furniture, String ceilingHeight, String safety, Boolean exchange, Long payment, Long rassrochkaPrice,
            String propertyType, String region, String city, AdModeratorStatusEnum moderatorStatus) {
    this.description = description;
    this.roomsCount = roomsCount;
    this.houseNumber = houseNumber;
    this.floor = floor;
    this.floorsCount = floorsCount;
    this.creationYear = creationYear;
    this.price = price;
    this.user = user;
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
  }
}
