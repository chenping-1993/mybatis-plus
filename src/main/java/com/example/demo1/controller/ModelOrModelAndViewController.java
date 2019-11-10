package com.example.demo1.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Model、ModelAndView 传到到html页面数据
 *
 * @Author: chenping
 * @Date: 2019/8/17
 */
@RestController
public class ModelOrModelAndViewController {

    @RequestMapping(value = "/testModelAndView", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView("testModelAndView");
        mv.addObject("userType", "66666666");
        return mv;
    }

    /**
     * Model类型，在页面上显示数据不支持
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/testModel", method = RequestMethod.GET)
    public String mode(Model model) {
        model.addAttribute("model", "model");
        return "testModel";
    }

    @RequestMapping(value = "/paramMap", method = RequestMethod.GET)
    public String paramMap(Map<String, Object> paramMap) {
        paramMap.put("name", "张三");
        paramMap.put("age", 35);
        return "paramMap";
    }
}
