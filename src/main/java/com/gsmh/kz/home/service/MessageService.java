package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.MessageResponse;
import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

  private MessageRepository messageRepository;

  public Message createMessage(RequestMessageDto requestMessageDto, Long fromUserId) {
    Message message = new Message();
    message.setAdsId(requestMessageDto.getAdsId());
    message.setFromUserId(fromUserId);
    message.setToUserId(requestMessageDto.getToUserId());
    message.setRead(false);
    message.setText(requestMessageDto.getText());
    return messageRepository.save(message);
  }

  public List<Message> getMessagesByToUsersAndAdsId(Long fromUserId, Long toUserId, Long adsId) {
    return messageRepository.getMessageBoxByUsers(fromUserId, toUserId, adsId);
  }



}
