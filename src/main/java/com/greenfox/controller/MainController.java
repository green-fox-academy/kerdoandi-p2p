package com.greenfox.controller;

import com.greenfox.Service.Log;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  MessageRepository messageRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String main(Model model, HttpServletRequest request){
    Log log = new Log(request.getRequestURI(),request.getMethod(), request.getParameter(null));
    log.print();

    if (userRepository.count() == 0)   {
      return "redirect:/enter";
    } else {
      model.addAttribute("username", userRepository.findOne((long)1).getName());
      model.addAttribute("messages", messageRepository.findAll());
      return "index";
    }
  }

  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String changeUserName(Model model, @RequestParam(value = "changed_username") String newName, HttpServletRequest request){
    Log log = new Log(request.getRequestURI(),request.getMethod(), request.getParameter("changed_username"));
    log.print();

    if (!newName.isEmpty()) {
      User user = userRepository.findOne((long) 1);
      user.setName(newName);
      userRepository.save(user);
    }
    return "redirect:/";
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String listUsers(Model model, HttpServletRequest request){
    Log log = new Log(request.getRequestURI(),request.getMethod(), request.getParameter("message"));
    log.print();

    model.addAttribute("users", userRepository.findAll());
    model.addAttribute("messages", messageRepository.findAll());
    return "userlist";
  }
}
