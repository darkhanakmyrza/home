package com.gsmh.kz.home.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "online_loan_requests")
public class OnlineLoanRequest extends Audit{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String iin;
  private String initialPayment;
  private String adsId;
  private String monthlySalary;
  private String userId;
}
