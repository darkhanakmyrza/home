package com.gsmh.kz.home.service;

import com.gsmh.kz.home.repository.VerificationCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class VerificationCodeService {

    private VerificationCodeRepository verificationCodeRepository;

    public String getVerificationCodeRandomString(){
        Random random = new Random();
        int number = random.nextInt(10000);
        return String.format("%04d", number);
    }
}
