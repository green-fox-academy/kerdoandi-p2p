package com.greenfox.model;

//@Entity
//@Table(name = "ClientIds")
public class Client {
//  @Id
  String id;

  public Client() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
