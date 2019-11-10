package com.example.demo1.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试异步任务
 *
 * @Author: chenping
 * @Date: 2019/8/13
 */
@RestController
public class AsyncTaskTestController {

    @Autowired
    AsyncTask asyncTask;

    @RequestMapping("/testAsync")
    public void testAsyncTask() throws InterruptedException {

        long start = System.currentTimeMillis();

        asyncTask.dojob1();
        asyncTask.dojob2();
        asyncTask.dojob3();

        long end = System.currentTimeMillis();
        System.out.println("执行异步测试任务共耗时: " + (end - start));

    }
}
