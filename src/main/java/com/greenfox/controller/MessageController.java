package com.greenfox.controller;

import com.greenfox.Log;
import com.greenfox.model.Message;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessageController {
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  UserRepository userRepository;

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String sendMessge(@RequestParam(value = "message", required = true) String message, HttpServletRequest request) {
    Log log = new Log(request.getRequestURI(),request.getMethod(), request.getParameter("message"));
    System.out.println(log);
    messageRepository.save(new Message(userRepository.findOne((long) 1).getName(), message));
    return "redirect:/";
  }
}
