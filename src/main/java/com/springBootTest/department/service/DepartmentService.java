package com.springBootTest.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootTest.department.entity.Department;
import com.springBootTest.department.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        log.info("Inside getAllUsers of DepartmentService");
        return departmentRepository.findAll();
    }


    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment of DepartmentService");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById of DepartmentService");
        return departmentRepository.findByDepartmentId(departmentId);
    }

    public Department updateDepartment(Long id, Department department){
        log.info("Inside updateDepartment of DepartmentService");
        
        Department oldDepartment = departmentRepository.findByDepartmentId(id);
        oldDepartment.setAddress(department.getAddress());
        oldDepartment.setName(department.getName());
        oldDepartment.setDescription(department.getDescription());
        oldDepartment.setManagerId(department.getManagerId());
        oldDepartment.setDepartmentCode(department.getDepartmentCode());
        departmentRepository.save(oldDepartment);

        return oldDepartment;
        

    }
}
