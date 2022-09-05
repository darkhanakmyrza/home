package com.gsmh.kz.home.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages_box")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageBox {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "messagesBoxIdSeq", sequenceName = "messages_box_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagesBoxIdSeq")
    private Long id;

    private Long adsId;

    private Long fromUserId;
    private Long toUserId;

    private Boolean fromUserBlocked;
    private Boolean toUserBlocked;

    private Long lastMessageId;


    @CreatedDate
    @Column(nullable = false)
    private Date createdDate;

}
