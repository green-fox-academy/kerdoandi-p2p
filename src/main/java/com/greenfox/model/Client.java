package com.greenfox.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ClientIds")
public class Client {
   String id;

  public Client() {
    this.id = System.getenv("CHAT_APP_UNIQUE_ID");
  }

  public String getId() {
    return id;
  }
}
