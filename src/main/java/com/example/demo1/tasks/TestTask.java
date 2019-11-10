package com.example.demo1.tasks;

import com.example.demo1.tasks.taskservice.PrintHelloworldService;
import com.example.demo1.tasks.taskservice.RuninFixedTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 扫描任务
 *
 * @Author: chenping
 * @Date: 2019/8/13
 */
@Component
public class TestTask {
    @Autowired
    private PrintHelloworldService printHelloworldService;
    @Autowired
    private RuninFixedTimeService runinFixedTimeService;


    /**
     * 每隔5秒执行一次定时任务
     *
     * fixedDelay控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，
     * 那么直到上一次执行完，并间隔给定的时间后，执行下一次
     *
     * fixedRate是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，
     * 但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行。
     */
//    @Scheduled(fixedDelay = 5000)
////    @Scheduled(fixedRate = 1000)
//    public void printHelloworld() {
//        printHelloworldService.printHelloworld();
//    }

    /**
     * 从 20秒到30秒，每1秒执行一次
     */
//    @Scheduled(cron = "20-30 * * * * ? ")
//    public void runinFixedTime() {
//        runinFixedTimeService.printFixedTimeEachMinute();
//    }
}
