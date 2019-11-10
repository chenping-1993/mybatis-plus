package com.example.demo1.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步任务，每个任务的执行互不干扰，互不等待
 * 若是不加 @Async 同时执行三个任务的时间为 三个时间相加；加 @Async，同时执行三个任务耗时几乎为0
 *
 * @Author: chenping
 * @Date: 2019/8/13
 */
@Component
public class AsyncTask {

    @Async
    public void dojob1() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务1执行时间：--" + (end - start));
    }

    @Async
    public void dojob2() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(700);
        long end = System.currentTimeMillis();
        System.out.println("任务2执行时间：--" + (end - start));
    }

    @Async
    public void dojob3() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(500);
        long end = System.currentTimeMillis();
        System.out.println("任务3执行时间：--" + (end - start));
    }
}
