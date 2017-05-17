package com.greenfox.controller;

import com.greenfox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PeerToPeerController {
  @Autowired
  MessageRepository messageRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getstatus(Model model) {
    model.addAttribute("message", messageRepository.findAll());
  return "index";
  }
}


