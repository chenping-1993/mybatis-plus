package com.example.demo1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1.entity.User;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.utils.DateUtils;
import com.example.demo1.utils.RedisUtil;
import com.example.demo1.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: chenping
 * @Date: 2019/7/23
 */
@RestController
public class TestController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    UserMapper userMapper;

    /**
     * 设置key value 过期时间
     *
     * @param body
     * @return
     */
    @PostMapping("/setValue")
    public boolean setValue(@RequestBody JSONObject body) {
        String key = body.getString("key");
        String value = body.getString("value");
        long time = body.getLongValue("time");

        boolean isSet = redisUtil.set(key, value, time);

        return isSet;
    }

    /**
     * 获取string类型的 value值
     *
     * @param key
     * @return
     */
    @RequestMapping("/getValue/{key}")
    public String getValues(@PathVariable String key) {

        String value = redisUtil.getStr(key);
        return value;
    }

    /**
     * 删除key
     *
     * @param key
     */
    @GetMapping("/delete/{key}")
    public void delete(@PathVariable String key) {
        redisUtil.del(key);
    }

    /**
     * 获取redis 剩余过期时间
     */
    @GetMapping("/expire/{key}")
    public long getExpire(@PathVariable String key) {
        return redisUtil.getExpire(key);
    }

    @GetMapping("/getByIds")
    public List<User> selectBatchIds() {

        List<Integer> list = new ArrayList<>();
        list.add(101);
        list.add(102);
        list.add(201);
        return userMapper.selectBatchIds(list);
    }

    @GetMapping("/selectByMap")
    public List<User> selectByMap() {
        //map 中的key 为数据库表中字段，非实体类的字段名称
        Map<String, Object> map = new HashMap<>();
        map.put("id", 101);
        map.put("name", "jack");
        return userMapper.selectByMap(map);
    }

    /**
     * queryWrapper 查询条件构造器
     * like ---》    %column%
     * likeLeft --->    %column
     * likeRight-->     column%
     * age < 40 and name like 'xiao%'
     * lt 小于 <； le 小于等于 <= ；gt 大于  >；ge大于等于 >=
     *
     * @return
     */
    @GetMapping("/selectList")
    public List<User> selectList() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 40).likeRight("name", "xiao");

        return userMapper.selectList(queryWrapper);
    }

    /**
     * queryWrapper 查询条件构造器
     * between age >=20  and age <= 20
     *
     * @return
     */
    @GetMapping("/selectList2")
    public List<User> selectList2() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 40).likeRight("name", "xiao").isNotNull("manager_id");

        return userMapper.selectList(queryWrapper);
    }

    /**
     * queryWrapper 查询条件构造器
     * between age >22  and age <= 55
     *
     * @return
     */
    @GetMapping("/selectList3")
    public List<User> selectList3() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 22).le("age", 55).or().gt("manager_id", 101);

        return userMapper.selectList(queryWrapper);
    }

    /**
     * name 为 xiao 并且（年龄小于50或者manager_id不为null）
     *
     * @return
     */
    @GetMapping("/selectList4")
    public List<User> selectList4() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "xiao").and(qw -> qw.lt("age", 50).or().isNotNull("manager_id"));

        return userMapper.selectList(queryWrapper);
    }

    /**
     * name 为 xiao 或者（年龄小于50 大于20并且manager_id不为null）
     *
     * @return
     */
    @GetMapping("/selectList5")
    public List<User> selectList5() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "xiao").or(qw -> qw.lt("age", 50).gt("age", 20).isNotNull("manager_id"));

        return userMapper.selectList(queryWrapper);
    }

    /**
     * age in 22 23 55
     *
     * @return
     */
    @GetMapping("/selectList6")
    public List<User> selectList6() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(22, 23, 55));

        return userMapper.selectList(queryWrapper);
    }

    /**
     * 只查询一部分字段
     *
     * @return
     */
    @GetMapping("/selectNotAllList")
    public List<User> selectNotAllList() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age").in("age", Arrays.asList(22, 23, 55));

        return userMapper.selectList(queryWrapper);
    }

    /**
     * 根据字段是否为空判断是否传递参数
     *
     * @return
     */
    @GetMapping("/queryColumnIsOrNot")
    public List<User> queryColumnIsOrNot() {
        String name = "";
        Integer age = 23;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(!StringUtils.isEmpty(name), "name", name).eq(age != null, "age", age);

        return userMapper.selectList(queryWrapper);
    }

    /**
     * @return
     */
    @GetMapping("/queryByMySqlSelectAll")
    public List<User> queryByMySql() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "xiao");


        return userMapper.selectAll(queryWrapper);
    }

    /***
     * 分页
     * @return
     */
    @GetMapping("/selectPage")
    public IPage<User> selectPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Page<User> page = new Page<>(1, 2);


        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);

        for (User user : iPage.getRecords()) {
            System.out.println(user);
        }

        System.out.println("总页数：--" + iPage.getPages());
        System.out.println("总记录数：--" + iPage.getTotal());
        return iPage;
    }

    public static void main(String[] args) {
        String str = "aaaa-bbbb-cccc-ddd-eee-fff-ggg-hhh-iii-jjjj-kkk-lll-mmm-nn-ooo-ppp-qqq-rrr-sss-ttt-uuu-vv-www-xxx-yy-zz";

//        long startTime = System.currentTimeMillis();
//        String[] str1 = str.split("-");
//        System.out.println(System.currentTimeMillis()-startTime);
//
//        long startTime1 = System.currentTimeMillis();
//        String[] str2 = StringUtils.split(str,"-");
//        System.out.println(System.currentTimeMillis()-startTime1);

//        String string = "";
//        String[] str1 = string.split("-");
//        for (int i = 0; i < str1.length; i++) {
//            System.out.println(str1[i]);
//        }
////        System.out.println(str1.toString());
//        String[] str2 = StringUtils.split(string,"-");
////        System.out.println(str2.toString());
//        for (int i = 0; i < str2.length; i++) {
//            System.out.println(str2[i]);
//        }
//        String string = "abcdefg";
//        long startTime2 = System.currentTimeMillis();
//        StringUtils.isPrime(131);
//        System.out.println(System.currentTimeMillis()-startTime2);

//        long startTime1 = System.currentTimeMillis();
        StringUtils.isPrime(4);
//        System.out.println(System.currentTimeMillis()-startTime1);

//        System.out.println(DateUtils.daysOfMonth());
//        switch("1"){
//            case "0" :
//                System.out.println("蓝色");
//                break;
//            case "1" :
//                System.out.println("黄色");
//                break;
//            case "2" :
//                System.out.println("黑色");
//                break;
//            default :
//                System.out.println("---");
//        }
//
//        switch("2"){
//            case "0" :
//                System.out.println("蓝色");
//                break;
//            case "1" :
//                System.out.println("黄色");
//                break;
//            case "2" :
//                System.out.println("黑色");
//                break;
//            default :
//                System.out.println("---");
//        }

//        System.out.println(DateUtils.formatStrdateToStrdate("2019-08-07 15:20:22.0", DateUtils.YYYYMMDDHHMMSS));


    }

}
