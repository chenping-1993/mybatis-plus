package com.example.demo1.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenping
 * @Date: 2019/10/21
 * 乐观锁插件
 */
@Configuration
public class MybatisPlusOptimisticLock {
    @Bean
   public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
