package com.greenfox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.greenfox.Service.MessageService;
import com.greenfox.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Message_SendController {
  @Autowired
  MessageService messageService;

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String sendMessge(@RequestParam(value = "message", required = true) String messageText, HttpServletRequest request) throws JsonProcessingException {
    Log log = new Log(request.getRequestURI(),request.getMethod(), request.getParameter("message"));
    log.print();

    messageService.saveAndSendMessage(messageText);
    return "redirect:/";
  }
}
