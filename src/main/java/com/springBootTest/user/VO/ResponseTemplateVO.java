package com.springBootTest.user.VO;

import com.springBootTest.user.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    
    private Users user;
    private Department department;
    private Event event;
}
