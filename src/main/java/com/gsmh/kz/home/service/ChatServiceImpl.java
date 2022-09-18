package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.model.entity.MessageBox;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.repository.MessageBoxRepository;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

  private SecurityService securityService;
  private MessageService messageService;
  private MessageBoxService messageBoxService;
  private MessageBoxRepository messageBoxRepository;

  @Override
  public void sendMessage(RequestMessageDto requestMessageDto) {
    User currentUser = securityService.getCurrentUser();
    Long messageBoxId = messageBoxService.findMessageBoxIfExits(currentUser.getId(), requestMessageDto.getToUserId());
    MessageBox messageBox = null;
    if (messageBoxId == null) {
      messageBox = messageBoxService.createMessageBox(currentUser.getId(), requestMessageDto.getToUserId());
    } else {
      messageBox = messageBoxService.getById(messageBoxId);
    }
    System.out.println("saved messageBox");
    Message message = messageService.createMessage(requestMessageDto, currentUser.getId());
    System.out.println("saved message");
    messageBox.setText(requestMessageDto.getText());
    messageBoxRepository.save(messageBox);
//    messageBoxService.saveMessageBox(messageBox, requestMessageDto, currentUser, message.getId());
    System.out.println("saved updated messageBox");

  }

  public List<Message> getMessages(Long toUserId, Long adsId) {
    return messageService.getMessagesByToUsersAndAdsId(securityService.getCurrentUser().getId(), toUserId, adsId);
  }

//    public List<Message> getChatByUser(){
//
//    }
}
