package com.example.demo1.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;

/**
 * @Author: chenping
 * @Date: 2019/6/12
 */
@Data
@TableName(value = "delete_example")//指定表名
public class DeleteExample {
    private Long id;

    private String name;

    private Integer age;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除表示 0 未删除 1 已删除
     */
    @TableLogic
    @TableField(select = false)//设置，在查询的时候不显示此字段
    private Integer deleted;

}
