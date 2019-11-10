package com.example.demo1.service;

import com.example.demo1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenping
 * @Date: 2019/6/12
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
}
