package com.gsmh.kz.home.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "messages_box")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageBox extends Audit {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "messagesIdSeq", sequenceName = "messages_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagesIdSeq")
  private Long id;

  private Long lastMessageId;
  private String text;
  private Long adsId;

  private Long fromUserId;
  private Long toUserId;

  private Boolean read;
  private Date readDate;
}
