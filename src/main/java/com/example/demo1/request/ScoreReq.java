package com.example.demo1.request;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: chenping
 * @Date: 2019/6/12
 */
@Data
@NoArgsConstructor
public class ScoreReq {

    private String subject;

    private Integer score;

    private Integer id;


    private String name;
}
