package com.example.demo1.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo1.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: chenping
 * @Date: 2019/6/12
 */
//@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
