package com.greenfox.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "Messages")
public class Message {
  int id;
  String username;
  String text;
  @Id
  Timestamp timestamp;

  public Message() {
  }

  public Message(String username, String text) {
    this.username = username;
    this.text = text;
    timestamp = Timestamp.valueOf(LocalDateTime.now());
    Random random = new Random();
    id = random.nextInt(8999999) + 1000000;
  }

  public String getUsername() {
    return username;
  }

  public String getText() {
    return text;
  }

  public int getId() {
    return id;
  }
}
