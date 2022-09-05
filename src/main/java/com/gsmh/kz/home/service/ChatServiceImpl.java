package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.MessageBox;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService{

    private MessageBoxService messageBoxService;
    private SecurityService securityService;

    @Override
    public void sendMessage(RequestMessageDto requestMessageDto) {
        User currentUser = securityService.getCurrentUser();
        MessageBox messageBox = messageBoxService.getMessageBoxByUsers(currentUser.getId(), requestMessageDto.getToUserId());

    }
}
