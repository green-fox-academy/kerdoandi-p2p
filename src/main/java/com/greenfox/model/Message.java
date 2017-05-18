package com.greenfox.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;

@Entity
@Table(name = "tb.message")
public class Message {
  String username;
  String text;
  @Id
  int id;


  public Message() {
  }

  public Message(String username, String text) {
    this.username = username;
    this.text = text;
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