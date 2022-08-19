package com.timetracker.tracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TASK_TRACKER")
public class TaskTracker implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_NUMBER",nullable = false, updatable = false)
	private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_TIME")
    private Date date = new Date(System.currentTimeMillis());

    @Column(name = "TASK_DETAILS", length = 5000)
    private String taskDetails;
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EMP_ID", nullable = false)
    @JsonIgnore
    private Employee employee;

    public TaskTracker(String taskDetails, Employee employee) {
        this.taskDetails = taskDetails;
        this.employee = employee;
    } 
    
    

    
}
