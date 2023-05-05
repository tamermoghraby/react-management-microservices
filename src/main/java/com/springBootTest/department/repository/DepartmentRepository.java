package com.springBootTest.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springBootTest.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentId(Long departmentId);
    
}
