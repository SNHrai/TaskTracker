package com.timetracker.tracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable{
    @Id
    private long id;
  
    @Column(name = "EMPLOYEENAME")
    private String employeeName;
  
    @Column(name = "EMPROLE")
    private String employeeRole;

    @Column(name = "EMPMOBILE")
    private Long mobileNumber;

    @Column(name = "EMP_VERTICLE_HEAD_ID")
    private long verticleHeadId;

    @OneToMany(targetEntity = TaskTracker.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
    private Collection<TaskTracker> taskTrackers = new ArrayList<TaskTracker>();
}
