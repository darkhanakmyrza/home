package com.gsmh.kz.home.model.dto;

import com.gsmh.kz.home.model.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ChatDto {
  private Long messageBoxId;
  private String name;
  private Long userId;
  private String status;
  private Date updatedDate;
  private String lastMessageText;
  private Message lastMessage;
  private String image;
}
