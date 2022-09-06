package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.RequestMessageDto;
import com.gsmh.kz.home.service.ChatService;
import com.gsmh.kz.home.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@PreAuthorize("isAuthenticated()")
@AllArgsConstructor
public class ChatController {

    private ChatService chatService;
    private MessageService messageService;
    private MessageBoxService messageBoxService;

//    @GetMapping("/myChat")
//    public ResponseEntity<MessageBox> getMessageBox(){
//
//    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestBody RequestMessageDto requestMessageDto){
        chatService.sendMessage(requestMessageDto);
        return ResponseEntity.ok().build();
    }
}
