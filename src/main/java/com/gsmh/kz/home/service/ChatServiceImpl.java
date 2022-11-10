package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.ChatDeleteDto;
import com.gsmh.kz.home.model.dto.ChatDto;
import com.gsmh.kz.home.model.dto.MessageDeleteDto;
import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.model.entity.MessageBox;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private SecurityService securityService;
    private MessageService messageService;
    private MessageBoxService messageBoxService;
    private UserService userService;
    private AdService adService;

    @Override
    public void sendMessage(RequestMessageDto requestMessageDto) {
        Long userId = securityService.getCurrentUserId();
        User currentUser = userService.getUser(userId);
        Ad ad = adService.getAd(requestMessageDto.getAdsId());
        if (ad == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ad not found or toUserId is wrong");
        }
        MessageBox messageBox = messageBoxService.findMessageBoxIfExits(currentUser.getId(), requestMessageDto.getToUserId(), requestMessageDto.getAdsId());
        if (messageBox == null) {
            messageBox = messageBoxService.createMessageBox(currentUser.getId(), requestMessageDto.getToUserId());
        }
        Message message = messageService.createMessage(requestMessageDto, currentUser.getId());
        messageBoxService.saveMessageBox(messageBox, requestMessageDto, currentUser, message.getId());
    }

    @Override
    public List<Message> getMessages(Long toUserId, Long adsId) {
        return messageService.getMessagesByToUsersAndAdsId(securityService.getCurrentUserId(), toUserId, adsId);
    }

    @Override
    public List<ChatDto> getChats() {
        Long currentUserId = securityService.getCurrentUserId();
        List<MessageBox> messageBoxes = messageBoxService.findMessageBoxesByUserId(currentUserId);
        return messageBoxes.stream().map(it -> messageBoxToChatDto(it, currentUserId)).toList();
    }

    @Override
    public void deleteAllByAdsId(Long adsId) {
        messageService.deleteAllByAdsId(adsId);
        messageBoxService.deleteAllByAdsId(adsId);
    }

    @Override
    public void deleteChatByAdsIdAndUserId(ChatDeleteDto chatDeleteDto) {
        Long fromUserId = securityService.getCurrentUserId();
        messageService.deleteAllByAdsIdAndUserId(chatDeleteDto.getAdsId(), fromUserId, chatDeleteDto.getUserId());
        messageBoxService.deleteAllByAdsIdAndUserId(chatDeleteDto.getAdsId(), fromUserId, chatDeleteDto.getUserId());
    }

    @Override
    public void deleteMessageById(MessageDeleteDto messageDeleteDto) {
        Long fromUserId = securityService.getCurrentUserId();
        MessageBox messageBox = messageBoxService.findMessageBoxIfExits(fromUserId, messageDeleteDto.getUserId(), messageDeleteDto.getAdsId());
        messageService.deleteById(messageDeleteDto.getMessageId());
        if (messageBox.getLastMessageId().equals(messageDeleteDto.getMessageId())) {
            Message newLastMessage = messageService.getLastMessageByUsersAndAdsId(fromUserId, messageDeleteDto.getUserId(), messageDeleteDto.getAdsId());
            if (newLastMessage != null) {
                messageBox.setLastMessageId(newLastMessage.getId());
                messageBox.setText(newLastMessage.getText());
                messageBoxService.saveEntity(messageBox);
            }else {
                messageBoxService.delete(messageBox);
            }
        }
    }

    private ChatDto messageBoxToChatDto(MessageBox messageBox, Long currentUserId) {
        User secondUser = userService.getUser(currentUserId.equals(messageBox.getToUserId()) ? messageBox.getFromUserId() : messageBox.getToUserId());
        Boolean amISender = messageBox.getFromUserId().equals(currentUserId);
        Message lastMessage = messageService.getById(messageBox.getLastMessageId());
        String status = amISender ? "Отправлено" : (messageBox.getRead() != null) ? "Сообщение" : "Новое сообщение";
        return new ChatDto(messageBox.getId(), secondUser.getName(), secondUser.getId(), status, messageBox.getUpdatedDate(), lastMessage.getText(), lastMessage, secondUser.getAvatarUrl(), messageBox.getAdsId());
    }

}
