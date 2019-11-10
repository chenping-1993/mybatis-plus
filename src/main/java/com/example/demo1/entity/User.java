package com.example.demo1.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @Author: chenping
 * @Date: 2019/6/12
 */
@Data
@TableName(value = "user")//指定表名
public class User {
    private Integer id;

    private String name;

    private Integer age;

    @TableField(value = "manager_id")
    private Integer managerId;

    /**
     * 数据库中不存在的字段
     */
    @Transient
    @TableField(exist = false)
    private String userName;
}
