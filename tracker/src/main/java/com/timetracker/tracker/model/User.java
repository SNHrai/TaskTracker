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
@Table(name = "EMP_MASTER")
public class User implements Serializable {
    @Id
    @Column(name = "EMP_ID")
    private long id;

    @Column(name = "EMP_NAME")
    private String userName;

    @Column(name = "EMP_ROLE")
    private String employeeRole;

    @Column(name = "EMP_MOBILE")
    private Long mobileNumber;

    @Column(name = "EMP_VERTICLE_HEAD_ID")
    private long verticleHeadId;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(targetEntity = TaskTracker.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
    private Collection<TaskTracker> taskTrackers = new ArrayList<TaskTracker>();

    public User(String employeeName) {
        this.userName = employeeName;
    }

    public User(long id, String employeeName) {
        this.id = id;
        this.userName = employeeName;
    }

    public User(long id, String employeeName, String employeeRole) {
        this.id = id;
        this.userName = employeeName;
        this.employeeRole = employeeRole;
    }

}
