package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.entity.MessageBox;
import com.gsmh.kz.home.repository.MessageBoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageBoxService {

    private MessageBoxRepository messageBoxRepository;

    public MessageBox getMessageBoxByUsers(Long fromUserId, Long toUserId){
        return messageBoxRepository.getMessageBoxByUsers(fromUserId, toUserId);
    }

}
