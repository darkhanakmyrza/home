package com.gsmh.kz.home.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "fav_ads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavAds extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ads_id")
    private Long adsId;
}
