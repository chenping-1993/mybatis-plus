package com.example.demo1.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author: chenping
 * @Date: 2019/10/21
 * 性能分析插件
 */
@Configuration
public class MybatisPlusPerformanceInterceptor {
    /**
     * @Description:  performanceInterceptor
     * @Param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
     * @Author: chenping
     * @Date: 2019/10/21
     * 会输出如下格式
     *  Time：39 ms - ID：com.example.demo1.mapper.DeleteExampleMapper.selectList
     * Execute SQL：SELECT id,name,age,create_time,update_time FROM delete_example WHERE deleted=0
     */
    @Bean
//    @Profile({"DEV","FAT"})//在开发环境和测试环境开启性能分析
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
