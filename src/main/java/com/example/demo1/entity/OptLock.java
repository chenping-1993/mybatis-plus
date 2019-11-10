package com.example.demo1.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @Author: chenping
 * @Date: 2019/10/21
 */
@Data
public class OptLock {
    private Integer id;

    private String name;

    @Version
    private Integer version;
}
