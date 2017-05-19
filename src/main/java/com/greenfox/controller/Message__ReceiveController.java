package com.greenfox.controller;

import com.greenfox.ErrorMessage;
import com.greenfox.Service.ReceivedMessageValidator;
import com.greenfox.model.MessageStatus;
import com.greenfox.model.MessageWithClientId;
import com.greenfox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Message__ReceiveController {
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private ReceivedMessageValidator receivedMessageValidator;

  @RequestMapping("/api/message/receive")
  @CrossOrigin("*")
  public Object receiveMessage(@RequestBody MessageWithClientId messageWithClientId) {
    receivedMessageValidator.setMissingParams(messageWithClientId);
    String missingParams = receivedMessageValidator.getMissingParams();

    if (!missingParams.isEmpty()) {
      return new ErrorMessage("Missing field(s): " + missingParams);
    } else {
      messageRepository.save(messageWithClientId.getMessage());
      return new MessageStatus();
    }
  }
}
