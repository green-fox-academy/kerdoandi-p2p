package com.greenfox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PeerToPeerController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getstatus(Model model) {
  return "index";
  }
}


