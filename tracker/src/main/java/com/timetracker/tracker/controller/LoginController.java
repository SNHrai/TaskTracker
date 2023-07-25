package com.timetracker.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.tracker.exception.ControllerException;
import com.timetracker.tracker.exception.ResourceNotFoundException;
import com.timetracker.tracker.model.AuthCredentialRequest;
import com.timetracker.tracker.model.User;
import com.timetracker.tracker.model.UserResponseModel;
import com.timetracker.tracker.repository.UserRepository;

@RestController
public class LoginController {

   @Autowired(required = true)
   private AuthenticationManager authenticationManager;

   @Autowired
   private UserRepository userRepository;

   private ControllerException controllerException;

   private String USER_NOT_FOUND_MSG = "USER NOT FOUND";

   @PostMapping("/login")
   public UserResponseModel login(@RequestBody AuthCredentialRequest request) {

      UserResponseModel userResponseModel = new UserResponseModel();

      // Authentication authentication = authenticationManager
      // .authenticate(
      // new UsernamePasswordAuthenticationToken(
      // request.getUserName(), request.getPassword()
      // )
      // );

      if (request.getUserName() != null && request.getPassword() != null) {
         User user = userRepository.findByUserName(request.getUserName());

         if (user.getUserName() != null) {
            userResponseModel.setEmployeeRole(user.getEmployeeRole());
            userResponseModel.setUserName(user.getUserName());
            userResponseModel.setVerticleHeadId(user.getVerticleHeadId());
            userResponseModel.setId(user.getId());

         } else {
            new UsernameNotFoundException(
                  String.format(USER_NOT_FOUND_MSG, request.getUserName()));
         }

      } else {
         new UsernameNotFoundException(
               String.format(USER_NOT_FOUND_MSG, request.getUserName()));
      }
      ;

      return userResponseModel;
   }

}
