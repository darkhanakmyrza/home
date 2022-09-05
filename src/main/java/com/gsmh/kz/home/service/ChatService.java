package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.RequestMessageDto;

public interface ChatService {

    public void sendMessage(RequestMessageDto requestMessageDto);
}
