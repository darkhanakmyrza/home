package com.gsmh.kz.home.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "verification_code")
public class VerificationCode extends Audit{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String phone;
  private String code;

}
