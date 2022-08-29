package com.gsmh.kz.home.model.dto;

import com.gsmh.kz.home.model.entity.Ad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AdsResponse {
  Integer count;
  List<Ad> list;
}
