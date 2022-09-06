package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.model.entity.Message;
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
public class ChatController {

    private ChatService chatService;
    private MessageService messageService;

//    @GetMapping("/myChat")
//    public ResponseEntity<MessageBox> getMessageBox(){
//
//    }

    @GetMapping("/chat")
    public ResponseEntity<List<Message>> getMessages(@PathVariable RequestMessageDto requestMessageDto){
        return ResponseEntity.ok(chatService.getMessages(requestMessageDto.getToUserId(), requestMessageDto.getAdsId() ));
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestBody RequestMessageDto requestMessageDto){
        chatService.sendMessage(requestMessageDto);
        return ResponseEntity.ok().build();
    }
}
