package com.gsmh.kz.home.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "messagesIdSeq", sequenceName = "messages_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagesIdSeq")
    private Long id;


    private Long adsId;

    private Long fromUserId;
    private Long toUserId;

    private Boolean read;
    private Date readDate;

    @CreatedDate
    @Column(nullable = false)
    private Date createdDate;

}
