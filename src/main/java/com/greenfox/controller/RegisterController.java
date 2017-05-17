package com.greenfox.controller;

import com.greenfox.model.User;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
  String error = "";

  @Autowired
  UserRepository userRepository;

  @GetMapping(value = "/enter")
  public String enter(Model model){
    model.addAttribute("users", userRepository);
    model.addAttribute("error", error);
    if (userRepository.count() > 0) {
      return "redirect:/";
    } else {
      return "enter";
    }
  }

  @PostMapping(value = "/enter/add")
  public String addNewUser(@RequestParam(value = "new_user", required = true) String name){
    if (name.isEmpty()) {
      error = "The username field is empty";
      return "redirect:/enter";
    } else {
      userRepository.save(new User(name));
      return "redirect:/";
    }
  }
}
