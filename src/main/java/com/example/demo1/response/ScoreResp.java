package com.example.demo1.response;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: chenping
 * @Date: 2019/6/12
 */
@Data
@NoArgsConstructor
public class ScoreResp {

    private String subject;

    private Integer score;

    private Integer id;


    private String name;
}
