package com.gsmh.kz.home.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Audit {

  @CreatedDate
  @Column(name = "created_date", nullable = false, updatable = false)
  private Date createdDate;

  @LastModifiedDate
  @Column(nullable = false)
  private Date updatedDate;

  @CreatedBy
  @Column(nullable = true)
  private Long createdBy;

}
