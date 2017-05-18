package com.greenfox.model;

public class MessageWithClientId {
  Message message;
  Client clientId;


  public MessageWithClientId(Message message, Client client) {
    this.message = message;
    this.clientId = client;
  }

  public Message getMessage() {
    return message;
  }

  public Client getClientId() {
    return clientId;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public void setClientId(Client client) {
    this.clientId = client;
  }
}
