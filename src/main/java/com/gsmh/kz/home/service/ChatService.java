package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;

import java.util.List;

public interface ChatService {

    public void sendMessage(RequestMessageDto requestMessageDto);
    public List<Message> getMessages(Long toUserId, Long adsId);
}
