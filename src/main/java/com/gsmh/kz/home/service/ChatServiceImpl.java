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
    MessageBox messageBox = messageBoxService.findMessageBoxIfExits(currentUser.getId(), requestMessageDto.getToUserId());
    if (messageBox == null) {
      messageBox = messageBoxService.createMessageBox(currentUser.getId(), requestMessageDto.getToUserId());
    }
    Message message = messageService.createMessage(requestMessageDto, currentUser.getId());
    messageBoxService.saveMessageBox(messageBox, requestMessageDto, currentUser, message.getId());
  }

  public List<Message> getMessages(Long toUserId, Long adsId) {
    return messageService.getMessagesByToUsersAndAdsId(securityService.getCurrentUser().getId(), toUserId, adsId);
  }

}
