package com.example.demo1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1.mapper.ScoreMapper;
import com.example.demo1.request.ScoreReq;
import com.example.demo1.response.ScoreResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenping
 * @Date: 2019/8/14
 */
@RestController
public class MybatisTestController {
    @Autowired
    ScoreMapper scoreMapper;

    /**
     * 自定义多表查询mybatis
     * 无参数
     *
     * @return
     */
    @GetMapping("/mybatisSelectAll")
    public List<ScoreResp> mybatisSelectAll() {
        return scoreMapper.selectScoreAndName();
    }

    /**
     * 自定义多表查询mybatis
     * 无参数
     *
     * @return
     */
    @GetMapping("/mybatisSelectByParams")
    public List<ScoreResp> mybatisSelectByParams() {

        QueryWrapper<ScoreReq> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("s.score", 88);
        return scoreMapper.selectScoreAndNameByParams(queryWrapper);
    }

    @GetMapping("/mybatisSelectByParams1")
    public List<ScoreResp> mybatisSelectByParams1() {
        ScoreReq req = new ScoreReq();
        req.setScore(88);
        return scoreMapper.selectScoreAndNameByParams1(req);
    }
}
