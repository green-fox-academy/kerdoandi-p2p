package com.greenfox.controller;

import com.greenfox.model.Client;
import com.greenfox.model.Message;
import com.greenfox.model.MessageStatus;
import com.greenfox.model.MessageWithClientId;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Message__ReceiveController {
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  UserRepository userRepository;

  @RequestMapping("/api/message/receive")
  public Object receiveMessage(@RequestBody MessageWithClientId messageWithClientId) {
    if (Message.class.isInstance(messageWithClientId.getMessage()) && Client.class.isInstance(messageWithClientId.getClientId())) {
      messageRepository.save(messageWithClientId.getMessage());
    }
    return new MessageStatus();
  }
}
