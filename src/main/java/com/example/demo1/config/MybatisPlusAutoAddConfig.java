package com.example.demo1.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: chenping
 * @Date: 2019/10/21
 * 自动填充配置类，自动写入更新时间和创建时间
 */
@Component
public class MybatisPlusAutoAddConfig implements MetaObjectHandler {
    /** 
     * @Description:  插入时填充
     * @Param: [metaObject] 
     * @return: void
     * @Author: chenping
     * @Date: 2019/10/21 
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //若是原始业务逻辑中没有设置创建时间，例如 没有执行 deleteExample.setCreateTime();
        // 或者是表数据中有创建时间字段执行下面的逻辑
        if (null == getFieldValByName("createTime",metaObject) || metaObject.hasGetter("createTime")) {
            setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
        }
    }

    /** 
     * @Description:  更新数据的时候自动更新更新字段值
     * @Param: [metaObject] 
     * @return: void 
     * @Author: chenping
     * @Date: 2019/10/21 
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //若是原始业务逻辑中没有设置创建时间，例如 没有执行 deleteExample.setUpdateTime();
        // 或者是表数据中有创建时间字段执行下面的逻辑
        if (null == getFieldValByName("updateTime",metaObject) || metaObject.hasGetter("updateTime")) {
            setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        }
    }
}
