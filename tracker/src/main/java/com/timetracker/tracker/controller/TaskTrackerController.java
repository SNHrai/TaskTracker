package com.timetracker.tracker.controller;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.timetracker.tracker.repository.EmployeeRepository;
import com.timetracker.tracker.repository.TaskTrackerRepository;

@RestController
@RequestMapping("/tracker")
public class TaskTrackerController {
    
    

    
    private EntityManagerFactory managerFactory; 
    private EmployeeRepository employeeRepository;
    private TaskTrackerRepository trackerRepository;


    @Autowired
    public TaskTrackerController(EmployeeRepository employeeRepository, TaskTrackerRepository trackerRepository, EntityManagerFactory managerFactory) {
        this.employeeRepository = employeeRepository;
        this.trackerRepository = trackerRepository;
        this.managerFactory = managerFactory;
    }


    @PostMapping("/tasktracker/{employeeId}")
    public ResponseEntity<TaskTracker> createTask(@PathVariable(value = "employeeId") Long employeeId,
        @RequestBody TaskTracker taskTracker) {
        TaskTracker tracker = employeeRepository.findById(employeeId).map(employee -> {
        taskTracker.setEmployee(employee);
        taskTracker.setDate(new Date());
        return trackerRepository.save(taskTracker);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found employee with id = " + employeeId));

       return new ResponseEntity<>(tracker, HttpStatus.CREATED);
  }

    @GetMapping("tasktracker/{verticleHeadId}")
    public ResponseEntity<List<TaskTracker>> findAllByVerticleHeadId(@PathVariable(value = "verticleHeadId") Long verticleHeadId){
        EntityManager manager = managerFactory.createEntityManager();
        Query query = manager.createQuery("select " +"e.employeeName, "+ "t.taskDetails from Employee e, " + "TaskTracker t where e.id = t.employee "  + "and e.verticleHeadId = "+verticleHeadId+"");
        // if(employeeRepository.findByVerticleHeadId(verticleHeadId).equals(null)){
        //   throw new ResourceNotFoundException("Not found with head ID  : " + verticleHeadId);
        // }
        List<TaskTracker> trackers = (List<TaskTracker>)query.getResultList();
        manager.close();
       return new ResponseEntity<>(trackers, HttpStatus.OK);
    }

    // @GetMapping("/tracker/{date}")
    // public ResponseEntity<List<TaskTracker>> findByDate(@PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
      
    //   List<TaskTracker> trackers = trackerRepository.findByDate(date);
    //   return new ResponseEntity<>(trackers, HttpStatus.OK);
    // }

    @GetMapping("/tracker/{date}")
    public ResponseEntity<List<TaskTracker>> findAllByDate(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MMM-yy") Date date) throws ParseException{
      // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
      DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy"); 
      //date = Calendar.getInstance().getTime();  
     
      //dateFormat.format(date); 
      EntityManager manager = managerFactory.createEntityManager();
      Query query = manager.createQuery("select " +"e.employeeName, "+ "t.taskDetails from Employee e, " + "TaskTracker t where e.id = t.employee "  + "and to_char(t.date, 'dd-Mon-yy') = '" +  dateFormat.format(date) +"'");
      // if(employeeRepository.findByVerticleHeadId(verticleHeadId).equals(null)){
      //   throw new ResourceNotFoundException("Not found with head ID  : " + verticleHeadId);
      // }
      List<TaskTracker> trackers = (List<TaskTracker>)query.getResultList();
      manager.close();

      
     
       
      // SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");  
      // formatter6.; 
     return new ResponseEntity<>(trackers, HttpStatus.OK);


    }




    @GetMapping("/tracker/currentdate")
    public ResponseEntity<List<TaskTracker>> getTaskBycurrentDate() throws ParseException{
      // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
      DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy"); 
      //date = Calendar.getInstance().getTime();  
     
      //dateFormat.format(date); 
      EntityManager manager = managerFactory.createEntityManager();
      Query query = manager.createQuery("select " +"e.employeeName, "+ "t.taskDetails from Employee e, " + "TaskTracker t where e.id = t.employee "  + "and to_char(t.date, 'dd-Mon-yy') = to_char(sysdate, 'dd-Mon-yy')");
      // if(employeeRepository.findByVerticleHeadId(verticleHeadId).equals(null)){
      //   throw new ResourceNotFoundException("Not found with head ID  : " + verticleHeadId);
      // }
      List<TaskTracker> trackers = (List<TaskTracker>)query.getResultList();
      manager.close();

      
     
       
      // SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");  
      // formatter6.; 
     return new ResponseEntity<>(trackers, HttpStatus.OK);


    }
    
    // @GetMapping("/tracker/{date}")
    // public ResponseEntity<List<TaskTracker>> findAllByDate(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MON-yy") Date date) throws ParseException{
    //   // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    //   //date = Calendar.getInstance().getTime();  
    //   DateFormat dateFormat = new SimpleDateFormat("dd-MON-yy"); 
     
    //   //dateFormat.format(date); 
    //   EntityManager manager = managerFactory.createEntityManager();
    //   Query query = manager.createQuery("select " +"e.employeeName, "+ "t.taskDetails from Employee e, " + "TaskTracker t where e.id = t.employee "  + "and t.date = '" +   dateFormat.format(date) + "%" +"'");
    //   // if(employeeRepository.findByVerticleHeadId(verticleHeadId).equals(null)){
    //   //   throw new ResourceNotFoundException("Not found with head ID  : " + verticleHeadId);
    //   // }
    //   List<TaskTracker> trackers = (List<TaskTracker>)query.getResultList();
    //   manager.close();

      
     
       
    //   // SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd");  
    //   // formatter6.; 
    //  return new ResponseEntity<>(trackers, HttpStatus.OK);


    // }
    
}
