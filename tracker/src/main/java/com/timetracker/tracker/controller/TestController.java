package com.timetracker.tracker.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timetracker.tracker.model.User;

@Controller
public class TestController {

    @GetMapping("/")
    public String helloWorld(Principal principal, Authentication authentication, Model model){

         String userName = principal.getName();


         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

          model.addAttribute("userName", userName);
          model.addAttribute("roles", authorities);
  
        return "admin";
    }


    // @GetMapping("/admin")
    // public String serviceHandler(Model model){

    //     model.addAttribute("title", "hello guys");
    //     return "admin.html";
    // }

  
    
    @GetMapping("/signin")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/manager")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView employee(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }


}
