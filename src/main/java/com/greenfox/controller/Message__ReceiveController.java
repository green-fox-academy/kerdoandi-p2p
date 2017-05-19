package com.greenfox.controller;

import com.greenfox.ErrorMessage;
import com.greenfox.Service.ReceivedMessageValidator;
import com.greenfox.model.MessageStatus;
import com.greenfox.model.MessageWithClientId;
import com.greenfox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Message__ReceiveController {
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  ReceivedMessageValidator receivedMessageValidator;

  @RequestMapping("/api/message/receive")
  public Object receiveMessage(@RequestBody MessageWithClientId messageWithClientId) {
    receivedMessageValidator.setMissingParams(messageWithClientId);
    String missingParams = receivedMessageValidator.getMissingParams();

    if (!missingParams.isEmpty()) {
      ErrorMessage errorMessage = new ErrorMessage("Missing field(s): " + missingParams);
      return errorMessage;
    } else {
      messageRepository.save(messageWithClientId.getMessage());
      MessageStatus messageStatus = new MessageStatus();
      return messageStatus;
    }
  }
}
