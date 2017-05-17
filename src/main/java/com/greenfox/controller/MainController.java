package com.greenfox.controller;

import com.greenfox.model.User;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
  String error = "";

  @Autowired
  UserRepository userRepository;

  @RequestMapping("/")
  public String main(Model model){

    if (userRepository.count() == 0) {
      return "redirect:/enter";
    } else {
      model.addAttribute("username", userRepository.findOne((long)1).getName());
      return "index";
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String changeUserName(Model model, @RequestParam(value = "changed_username") String newName){
    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    if (newName.isEmpty()) {
      error = "The username field is empty";
      return "redirect:/";
    } else {
      User user = userRepository.findOne((long) 1);
      user.setName(newName);
      userRepository.save(user);
      return "redirect:/";
    }
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String listUsers(Model model){
    model.addAttribute("users", userRepository.findAll());
    return "userlist";
  }
}
