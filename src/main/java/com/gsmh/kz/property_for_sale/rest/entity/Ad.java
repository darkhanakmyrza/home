package com.gsmh.kz.property_for_sale.rest.entity;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "adsIdSeq", sequenceName = "ads_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adsIdSeq")
  private int id;


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
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  public Ad() {
  }

  public Ad(int id, String description, int roomsCount,
            int houseNumber, int floor, int floorsCount,
            int creationYear, int price) {
    this.id = id;
    this.description = description;
    this.roomsCount = roomsCount;
    this.houseNumber = houseNumber;
    this.floor = floor;
    this.floorsCount = floorsCount;
    this.creationYear = creationYear;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getRoomsCount() {
    return roomsCount;
  }

  public void setRoomsCount(int roomsCount) {
    this.roomsCount = roomsCount;
  }

  public int getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(int houseNumber) {
    this.houseNumber = houseNumber;
  }

  public int getFloor() {
    return floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getFloorsCount() {
    return floorsCount;
  }

  public void setFloorsCount(int floorsCount) {
    this.floorsCount = floorsCount;
  }

  public int getCreationYear() {
    return creationYear;
  }

  public void setCreationYear(int creationYear) {
    this.creationYear = creationYear;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }


  @Override
  public String toString() {
    return "Ad{" +
           "id=" + id +
           ", description='" + description + '\'' +
           ", roomsCount=" + roomsCount +
           ", houseNumber=" + houseNumber +
           ", floor=" + floor +
           ", floorsCount=" + floorsCount +
           ", creationYear=" + creationYear +
           ", price=" + price +
           '}';
  }
}
