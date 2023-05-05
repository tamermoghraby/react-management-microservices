package com.springBootTest.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springBootTest.user.entity.Users;
import com.springBootTest.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        
        return new CustomUserDetails(user);
    }
    
}
