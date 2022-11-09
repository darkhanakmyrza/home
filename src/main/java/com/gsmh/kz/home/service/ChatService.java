package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.ChatDto;
import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.model.entity.MessageBox;

import java.util.List;

public interface ChatService {

    void sendMessage(RequestMessageDto requestMessageDto);
    List<Message> getMessages(Long toUserId, Long adsId);
    List<ChatDto> getChats();
    void deleteAllByAdsId(Long adsId);
}
