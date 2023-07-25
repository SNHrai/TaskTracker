package com.timetracker.tracker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    @Autowired
    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    // public MyUserDetails(User user) {
    // this.userName = user.getUserName();
    // this.password = user.getPassword();
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // HashSet<SimpleGrantedAuthority> set = new HashSet<>();
        // set.add(new SimpleGrantedAuthority(this.user.getEmployeeRole()));
        // return set;
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getEmployeeRole());
        return Collections.singletonList(authority);

        // return Arrays.asList(new SimpleGrantedAuthority("EMPLOYEE"));
    } 

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {

    // return user.getEmployeeRole();
    // }
    @Override
    public String getPassword() {

        return this.user.getPassword();
    }

    @Override
    public String getUsername() {

        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

}
