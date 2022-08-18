package com.timetracker.tracker.repository;







import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timetracker.tracker.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

    
     public Employee findByVerticleHeadId(Long verticleHeadId);
    
}
