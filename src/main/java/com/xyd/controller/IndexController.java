package com.xyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/24/19:31
 * @Description:
 */

@RestController
@RequestMapping("/manager")
public class IndexController {
    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView index = new ModelAndView("/index");
        return index;
    }
}