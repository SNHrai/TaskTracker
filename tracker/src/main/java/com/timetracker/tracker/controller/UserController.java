package com.timetracker.tracker.controller;

import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.tracker.exception.ResourceNotFoundException;
import com.timetracker.tracker.model.TaskTracker;
import com.timetracker.tracker.repository.TaskTrackerRepository;
import com.timetracker.tracker.repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {

    private EntityManagerFactory managerFactory; 
    private UserRepository userRepository;
    private TaskTrackerRepository trackerRepository;


    @Autowired
    public UserController(UserRepository employeeRepository, TaskTrackerRepository trackerRepository, EntityManagerFactory managerFactory) {
        this.userRepository = employeeRepository;
        this.trackerRepository = trackerRepository;
        this.managerFactory = managerFactory;
    }
    
    @GetMapping("/")
    public String getMsg(){
        return "Welcome User";
 }

    @PostMapping("/tasktracker/{employeeId}")
    public ResponseEntity<TaskTracker> createTask(@PathVariable(value = "employeeId") Long employeeId, @RequestBody TaskTracker taskTracker) {
        TaskTracker tracker = userRepository.findById(employeeId).map(user -> {
        taskTracker.setUser(user);
        taskTracker.setDate(new Date());
        return trackerRepository.save(taskTracker);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found employee with id = " + employeeId));

       return new ResponseEntity<>(tracker, HttpStatus.CREATED);
 }

}
