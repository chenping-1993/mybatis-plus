package com.example.demo1;

import com.example.demo1.entity.DeleteExample;
import com.example.demo1.entity.OptLock;
import com.example.demo1.entity.User;
import com.example.demo1.mapper.DeleteExampleMapper;
import com.example.demo1.mapper.OptLockMapper;
import com.example.demo1.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * mybatis-plus 测试类
 * @Author: chenping
 * @Date: 2019/6/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class Test1 {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DeleteExampleMapper deleteExampleMapper;

    @Autowired
    OptLockMapper optLockMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void select() {
        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(4, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void redisTest() {
        stringRedisTemplate.opsForValue().set("aaa", "1111");
    }

    /**
     * 删除的实质是更新了 deleted标识
     */
    @Test
    public void testDelete() {

        int result = deleteExampleMapper.deleteById(1);
        System.out.println(result);
    }

    /**
     * 只查询 deleted 标识为未删除的数据，更新也是一样
     */
    @Test
    public void testSelectDeleteList() {

        List<DeleteExample> result = deleteExampleMapper.selectList(null);
        System.out.println(result.toString());
    }

    /** 
     * @Description:  插入数据自动添加创建时间
     * @Param: [] 
     * @return: void 
     * @Author: chenping
     * @Date: 2019/10/21 
     */
    @Test
    public void testInsertTime() {

        DeleteExample deleteExample = new DeleteExample();
        deleteExample.setAge(33);
        deleteExample.setName("叁");
        int result = deleteExampleMapper.insert(deleteExample);
        System.out.println(result);
    }

    @Test
    public void testUpdateTime() {

        DeleteExample deleteExample = new DeleteExample();
        deleteExample.setAge(34);
        deleteExample.setName("叁");
        deleteExample.setId(1186290379276337154L);
        int result = deleteExampleMapper.updateById(deleteExample);
        System.out.println(result);
    }

    /** 
     * @Description:  会将版本号version自动加一
     * @Param: [] 
     * @return: void 
     * @Author: chenping
     * @Date: 2019/10/21 
     */
    @Test
    public void testUpdateVersion() {
        OptLock optLock = new OptLock();
        optLock.setId(1);
        optLock.setName("aaaab");
        optLock.setVersion(1);
        int result = optLockMapper.updateById(optLock);
        System.out.println(result);
    }

}
