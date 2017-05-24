package com.greenfox.model;

public class MessageWithClientId {
  private Message message;
  private Client client;

  public MessageWithClientId(Message message, Client client) {
    this.message = message;
    this.client = client;
  }

  public MessageWithClientId() {}

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
