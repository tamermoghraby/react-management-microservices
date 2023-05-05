package com.springBootTest.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springBootTest.user.entity.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserId(Long userId);
    Users findByUserName(String username);
    Users findByEmail(String email);

    //@Query(value =  "select * from Users u where u.departmentId = :id")
    //List<Users> findUsersByDepId(@Param("id") Long departmentId);

}
