package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.ChatDeleteDto;
import com.gsmh.kz.home.model.dto.ChatDto;
import com.gsmh.kz.home.model.dto.MessageDeleteDto;
import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
import com.gsmh.kz.home.model.entity.MessageBox;
import com.gsmh.kz.home.service.ChatService;
import com.gsmh.kz.home.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
@CrossOrigin
public class ChatController {

    private ChatService chatService;
    private MessageService messageService;

    @GetMapping("/myChat")
    public ResponseEntity<List<ChatDto>> getMessageBox() {
        return ResponseEntity.ok(chatService.getChats());
    }

    @PostMapping("/user")
    public ResponseEntity<List<Message>> getMessages(@RequestBody RequestMessageDto requestMessageDto) {
        return ResponseEntity.ok(chatService.getMessages(requestMessageDto.getToUserId(), requestMessageDto.getAdsId()));
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestBody RequestMessageDto requestMessageDto) {
        chatService.sendMessage(requestMessageDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteChat(@RequestBody ChatDeleteDto chatDeleteDto) {
        chatService.deleteChatByAdsIdAndUserId(chatDeleteDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/message")
    public ResponseEntity<Void> deleteMessage(@RequestBody MessageDeleteDto messageDeleteDto) {
        chatService.deleteMessageById(messageDeleteDto);
        return ResponseEntity.ok().build();
    }

}
