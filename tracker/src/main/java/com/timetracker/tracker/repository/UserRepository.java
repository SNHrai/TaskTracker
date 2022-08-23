package com.timetracker.tracker.repository;










import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.timetracker.tracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

     // public List<Employee> findByPassword(String password);
    
     public User findByVerticleHeadId(Long verticleHeadId);

     // @Query("select u.userName from User u where userName = :userName")
     // @Query("select u from User u where u = :userName")
     @Query("select u from User u where u.userName = :userName")
     public User findByUserName(String userName);
    
}
 