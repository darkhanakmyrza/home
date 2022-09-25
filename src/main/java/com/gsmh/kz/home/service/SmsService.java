package com.gsmh.kz.home.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);
    @Value("${sms.provider.login}")
    private String smsProviderLogin;

    @Value("${sms.provider.password}")
    private String smsProviderPassword;

    @Value("${sms.provider.link}")
    private String smsProviderLink;
    @Autowired
    private ResTemplateService resTemplateService;


    public void sendSmsVerification(String phone, String message) {
        String link = smsProviderLink + "?login=" + smsProviderLogin + "&psw=" + smsProviderPassword + "&phones=" + phone + "&mes=" + message;
        String response = resTemplateService.getObject(link);
        logger.info("verification sms phone: " + phone + ", reponse : " + response);
    }
}
