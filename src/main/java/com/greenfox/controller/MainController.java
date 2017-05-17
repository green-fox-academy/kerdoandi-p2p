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
  @Autowired
  UserRepository userRepository;

  @RequestMapping("/")
  public String main(){
    return "index";
  }

  @RequestMapping("/enter")
  public String enter(Model model){
    model.addAttribute("users", userRepository);
    return "enter";
  }

  @RequestMapping(value = "/adduser", method = RequestMethod.POST)
  public String addNewUser(@RequestParam("new_user") String name){
    User user = new User(name);
    userRepository.save(user);
    return "index";
  }
}
