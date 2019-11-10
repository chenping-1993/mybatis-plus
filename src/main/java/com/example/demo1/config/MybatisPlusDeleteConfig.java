package com.example.demo1.config;

import org.springframework.context.annotation.Configuration;

/** 逻辑删除配置类，不删除数据，而是更新删除标识字段
 * @Author: chenping
 * @Date: 2019/10/20
 */
@Configuration
public class MybatisPlusDeleteConfig {

    /**
     * 3.1.1 及以后不需要此配置
     * @return
     */
//    @Bean
//    public ISqlInjector iSqlInjector () {
//        return new LogicSqlInjector();
//    }
}

