package com.springBootTest.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springBootTest.user.VO.Department;
import com.springBootTest.user.VO.ResponseTemplateVO;
import com.springBootTest.user.entity.Users;
import com.springBootTest.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Users> findAllUsers() {
        log.info("Inside findAllUsers of UserService");
        return userRepository.findAll();
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Users user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }

    public ResponseEntity<?> getUserById(Long userId) {
        log.info("Inside getUserById of UserService");
        Users user = userRepository.findByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok("User Not Found");
        }
    }

    public Users findByUsername(String username) {
        log.info("Inside findbyUsername of UserService");
        return userRepository.findByUserName(username);
    }

    public Users findByEmail(String email) {
        log.info("Inside findByEmail of UserService");
        return userRepository.findByEmail(email);
    }

    // public List<Users> findUsersByDepId(Long departmentId){
    // return userRepository.findUsersByDepId(departmentId);
    // }

    public Users saveUser(Users user) {
        log.info("Inside saveUser of UserService");

        return userRepository.save(user);
    }

    public Users updateUser(Long id, Users user) {
        log.info("Inside updateUser of UserService");
        Users oldUser = userRepository.findByUserId(id);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setRole(user.getRole());
        oldUser.setUserName(user.getUserName());
        oldUser.setEmail(user.getEmail());

        oldUser.setDepartmentId(user.getDepartmentId());

        userRepository.save(oldUser);
        return oldUser;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
