package com.greenfox.controller;

import com.greenfox.model.Message;
import com.greenfox.model.MessageStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

  @RequestMapping("/api/message/receive")
  public MessageStatus getMessageStatus() {


  }
}
