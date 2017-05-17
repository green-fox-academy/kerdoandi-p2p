//package com.greenfox.controller;
//
//import com.greenfox.model.Message;
//import com.greenfox.repository.MessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class MessageController {
//  @Autowired
//  MessageRepository messageRepository;
//
//  @RequestMapping(value = "/send", method = RequestMethod.POST)
//  public String sendMessge(@RequestParam(value = "message", required = true) String message) {
//    messageRepository.save(new Message(message));
//    return "redirect:/";
//  }
//}
