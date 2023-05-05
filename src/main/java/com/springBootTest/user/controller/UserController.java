package com.springBootTest.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springBootTest.user.VO.ResponseTemplateVO;
import com.springBootTest.user.entity.Users;
import com.springBootTest.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @Operation(description = "Get all available users", responses = {
            @ApiResponse(responseCode = "400", description = "Users not found"),
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public List<Users> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("")
    @Operation
    public ResponseEntity<?> getUserById(@RequestParam("id") Long id) {
        log.info("Inside getUserById of UserController");
        return userService.getUserById(id);
    }

    @GetMapping("/username")
    public Users findByUsername(@RequestParam("username") String username) {
        log.info("Inside findByUsername of UserController");
        return userService.findByUsername(username);
    }

    @GetMapping("/email")
    public Users findByEmail(@RequestParam("email") String email) {
        log.info("Inside findByEmail of UserController");
        return userService.findByEmail(email);
    }

    @GetMapping("/{id}")
    @Operation()
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);

    }

    // @GetMapping("/all")
    // public List<Users> getUsersFromDepartmentId(@RequestParam("id") Long
    // departmentId){
    // return userService.findUsersByDepId(departmentId);
    // }

    @PostMapping("/save")
    @Operation(description = "Save a new User")
    public Users saveUser(@RequestBody Users user) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable long id, @RequestBody Users user) {
        log.info("Inside updateUser of UserController");
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<String>("User successfully deleted", HttpStatus.OK);
    }

}
