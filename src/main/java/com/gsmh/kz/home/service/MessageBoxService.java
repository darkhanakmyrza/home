package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.model.entity.MessageBox;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.repository.MessageBoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageBoxService {

    private MessageBoxRepository messageBoxRepository;

    public MessageBox saveMessageBox(MessageBox messageBox, RequestMessageDto requestMessageDto, User currentUser, Long lastMessageId) {
        messageBox.setText(requestMessageDto.getText());
        messageBox.setAdsId(requestMessageDto.getAdsId());
        messageBox.setLastMessageId(lastMessageId);
        messageBox.setFromUserId(currentUser.getId());
        messageBox.setToUserId(requestMessageDto.getToUserId());
        return messageBoxRepository.save(messageBox);
    }

    public MessageBox saveEntity(MessageBox messageBox) {
        return messageBoxRepository.save(messageBox);
    }

    public MessageBox createMessageBox(Long fromUserId, Long toUserId) {
        MessageBox messageBox = new MessageBox();
        messageBox.setFromUserId(fromUserId);
        messageBox.setToUserId(toUserId);
        return messageBoxRepository.save(messageBox);
    }

    public MessageBox findMessageBoxIfExits(Long fromUserId, Long toUserId, Long adsId) {
        return messageBoxRepository.findMessageBoxIfExits(fromUserId, toUserId, adsId);
    }

    public List<MessageBox> findMessageBoxesByUserId(Long userId) {
        return messageBoxRepository.findMessageBoxByUserId(userId);
    }

    public MessageBox getById(Long id) {
        return messageBoxRepository.findById(id).orElseThrow(null);
    }

    public void deleteAllByAdsId(Long adsId) {
        messageBoxRepository.deleteAllByAdsId(adsId);
    }

    public void deleteAllByAdsIdAndUserId(Long adsId, Long fromUserId, Long toUserId){
        messageBoxRepository.deleteAllByAdsIdAndUserId(adsId, fromUserId, toUserId);
    }

    public void delete(MessageBox messageBox) {
        messageBoxRepository.delete(messageBox);
    }
}
