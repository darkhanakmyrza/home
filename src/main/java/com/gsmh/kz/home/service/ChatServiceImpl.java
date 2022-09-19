package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.ChatDto;
import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.model.entity.MessageBox;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.repository.MessageBoxRepository;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

  private SecurityService securityService;
  private MessageService messageService;
  private MessageBoxService messageBoxService;
  private MessageBoxRepository messageBoxRepository;
  private UserService userService;

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

  @Override
  public List<Message> getMessages(Long toUserId, Long adsId) {
    return messageService.getMessagesByToUsersAndAdsId(securityService.getCurrentUser().getId(), toUserId, adsId);
  }

  @Override
  public List<ChatDto> getChats() {
    Long currentUserId = securityService.getCurrentUserId();
    List<MessageBox> messageBoxes = messageBoxService.findMessageBoxesByUserId(currentUserId);
    return messageBoxes.stream().map(it -> messageBoxToChatDto(it, currentUserId)).toList();
  }

  private ChatDto messageBoxToChatDto(MessageBox messageBox, Long currentUserId) {
    User secondUser = userService.getUser(currentUserId.equals(messageBox.getToUserId()) ? messageBox.getFromUserId() : messageBox.getToUserId());
    Boolean amISender = messageBox.getFromUserId().equals(currentUserId);
    Message lastMessage = messageService.getById(messageBox.getLastMessageId());
    String status = amISender ? "Отправлено" : (messageBox.getRead()) ? "Сообщение" : "Новое сообщение";
    return new ChatDto(messageBox.getId(), secondUser.getName(), secondUser.getId(), status, messageBox.getUpdatedDate(), lastMessage.getText(), lastMessage, secondUser.getAvatarUrl());
  }

}
