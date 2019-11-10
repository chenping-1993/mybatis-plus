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
@TableName(value = "score")//指定表名
public class Score {

    private String subject;

    private Integer score;

    @TableField(value = "stu_id")
    private Integer stuId;

    /**
     * 数据库中不存在的字段
     */
    @Transient
    @TableField(exist = false)
    private String userName;
}
