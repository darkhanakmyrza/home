package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.MessageResponse;
import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    public Message createMessage(RequestMessageDto requestMessageDto, Long fromUserId){
        Message message = new Message();
        message.setAdsId(requestMessageDto.getAdsId());
        message.setFromUserId(fromUserId);
        message.setToUserId(requestMessageDto.getToUserId());
        message.setRead(false);
        return messageRepository.save(message);
    }
}
