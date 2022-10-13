package com.gsmh.kz.home.model.dto;

import com.gsmh.kz.home.model.entity.OnlineLoanRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineLoanRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String iin;
    private String initialPayment;
    private String adsId;
    private String monthlySalary;
    private String userId;

    public OnlineLoanRequest toEntity(){
        OnlineLoanRequest onlineLoanRequest = new OnlineLoanRequest();
        onlineLoanRequest.setFirstName(this.getFirstName());
        onlineLoanRequest.setLastName(this.getLastName());
        onlineLoanRequest.setIin(this.getIin());
        onlineLoanRequest.setInitialPayment(this.getInitialPayment());
        onlineLoanRequest.setAdsId(this.getAdsId());
        onlineLoanRequest.setMonthlySalary(this.getMonthlySalary());
        onlineLoanRequest.setUserId(this.getUserId());
        return onlineLoanRequest;
    }
}
