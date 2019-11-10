package com.example.demo1.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo1.entity.Score;
import com.example.demo1.entity.User;
import com.example.demo1.request.ScoreReq;
import com.example.demo1.response.ScoreResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: chenping
 * @Date: 2019/6/12
 */
//@Repository
public interface ScoreMapper extends BaseMapper<ScoreResp> {

    /**
     * 无参自定义mybatis
     *
     * @return
     */
    List<ScoreResp> selectScoreAndName();

    /**
     * 有参自定义mybatis
     * mybatis-plus 参数--QueryWrapper
     *
     * @param wrapper
     * @return
     */
    List<ScoreResp> selectScoreAndNameByParams(@Param(Constants.WRAPPER) Wrapper<ScoreReq> wrapper);

    /**
     * 有参自定义mybatis
     * 自定义请求参数
     *
     * @param params
     * @return
     */
    List<ScoreResp> selectScoreAndNameByParams1(@Param("params") ScoreReq params);


}
