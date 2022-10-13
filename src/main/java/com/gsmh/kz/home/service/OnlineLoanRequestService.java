package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.OnlineLoanRequestDto;
import com.gsmh.kz.home.model.entity.OnlineLoanRequest;
import com.gsmh.kz.home.repository.OnlineLoanRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OnlineLoanRequestService {
    private final OnlineLoanRequestRepository onlineLoanRequestRepository;

    public OnlineLoanRequest saveOnlineLoanRequest(OnlineLoanRequestDto onlineLoanRequestDto) {
        OnlineLoanRequest onlineLoanRequest = onlineLoanRequestDto.toEntity();
        return onlineLoanRequestRepository.save(onlineLoanRequest);
    }
}
