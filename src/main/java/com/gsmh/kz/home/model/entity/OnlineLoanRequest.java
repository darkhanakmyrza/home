package com.gsmh.kz.home.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "online_loan_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineLoanRequest extends Audit {

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
