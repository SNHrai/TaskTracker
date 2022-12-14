package com.timetracker.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.tracker.model.AuthCredentialRequest;
import com.timetracker.tracker.model.User;
@RestController
public class LoginController {
    
   @Autowired
   private AuthenticationManager authenticationManager;


   @PostMapping("/login")
   public Authentication login(@RequestBody AuthCredentialRequest request){
       Authentication authentication = authenticationManager 
       .authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUserName(), request.getPassword()
            )
       );
        
    return  authentication;
   }

  
}
