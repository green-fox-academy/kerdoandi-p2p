package com.greenfox.controller;

import com.greenfox.Service.ErrorMessage;
import com.greenfox.Service.MessageStatusOK;
import com.greenfox.Service.ReceivedMessageValidator;
import com.greenfox.Service.SendMessageService;
import com.greenfox.model.Log;
import com.greenfox.model.MessageWithClientId;
import com.greenfox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Message__ReceiveController {
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private ReceivedMessageValidator receivedMessageValidator;
  @Autowired
  SendMessageService sendMessageService;

  @RequestMapping("/api/message/receive")
  @CrossOrigin("*")
  public Object receiveMessage(@RequestBody MessageWithClientId messageWithClientId,
                               HttpServletRequest request) {
    Log log = new Log(request.getRequestURI(),request.getMethod(), request.getParameter("message"));
    log.print();

    receivedMessageValidator.setMissingParams(messageWithClientId);
    String missingParams = receivedMessageValidator.getMissingParams();
    if (!missingParams.isEmpty()) {
      return new ErrorMessage("Missing field(s): " + missingParams);
    } else {
      if (!messageWithClientId.getClient().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
        sendMessageService.sendMessage(messageWithClientId);
        messageRepository.save(messageWithClientId.getMessage());
      }
      return new MessageStatusOK();
    }
  }
}
