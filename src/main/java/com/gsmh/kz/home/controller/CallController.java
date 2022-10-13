package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.OnlineLoanRequestDto;
import com.gsmh.kz.home.service.OnlineLoanRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
@AllArgsConstructor
public class CallController {

    private final OnlineLoanRequestService onlineLoanRequestService;

    @PostMapping("/onlineLoanRequest")
    public ResponseEntity<?> onlineLoanRequest(@RequestBody OnlineLoanRequestDto onlineLoanRequestDto) {
        return ResponseEntity.ok(onlineLoanRequestService.saveOnlineLoanRequest(onlineLoanRequestDto));
    }
}
