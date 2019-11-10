package com.example.demo1.tasks.taskservice;

import com.example.demo1.utils.DateUtils;
import org.springframework.stereotype.Service;

/**
 * 固定时间执行的定时任务
 *
 * @Author: chenping
 * @Date: 2019/8/13
 */
@Service
public class RuninFixedTimeService {

    public void printFixedTimeEachMinute() {
        System.out.println(DateUtils.getCurDateTime(DateUtils.YYYY_MM_DD_HH_MM_SS));
    }
}
