package com.example.demo1.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: chenping
 * @Date: 2019/5/20
 */
@Getter
@Setter
public class People {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
