package com.example.demo1.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.demo1.entity.User;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenping
 * @Date: 2019/7/8
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/getUsers")
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @RequestMapping("/selectById")
    public User selectById() {
        return userMapper.selectById(101);
    }

    @RequestMapping("/insertUsers")
    public int insert() {
        User user = new User();
        user.setId(204);
        user.setAge(66);
        user.setName("cart");
        user.setManagerId(102);
        return userMapper.insert(user);

    }

    @RequestMapping("/updateUser")
    public int updateUser() {
        User user = new User();
        user.setId(204);
        user.setAge(55);
        return userMapper.updateById(user);//不传的字段值不更新


    }

    @RequestMapping("/getByColumn")
    public List<User> getByColumn() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("id", 204);//写表中的列名
        columnMap.put("age", 55);
        return userMapper.selectByMap(columnMap);
    }

    @RequestMapping("/batchSelect")
    public List<User> batchSelect() {
        List<Integer> listIds = new ArrayList<>();
        listIds.add(101);
        listIds.add(102);
        return userMapper.selectBatchIds(listIds);
    }
}
