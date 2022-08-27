package com.gsmh.kz.property_for_sale.rest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

}
