package com.springBootTest.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springBootTest.department.entity.Department;
import com.springBootTest.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("")
    public List<Department> getAllDepartments(){
        log.info("Inside getAllDepartments of DepartmentController");
        return departmentService.getAllDepartments();
    }

    //@GetMapping("/departmentUsers/{id}")
    //public List<?> getDepartmentUsers(@PathVariable("id") Long departmentId){
    //    List<Users> users = new List<Users>();
    //    return users ;
    //}

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentById of DepartmentController");
        return departmentService.findDepartmentById(departmentId);

    }

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside saveDepartment of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/update/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department){
        log.info("Inside updateDepartment of DepartmentController");
        return departmentService.updateDepartment(id, department);
    }


}
