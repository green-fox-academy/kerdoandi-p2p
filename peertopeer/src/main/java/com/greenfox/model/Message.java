package com.greenfox.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Component
@Getter
@Setter
public class Message {
  String message;

  public Message() {
  }
}
