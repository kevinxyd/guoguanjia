package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Examine;
import com.xyd.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/26/15:23
 * @Description:
 */

@RestController
@RequestMapping("manager/examine")
public class ExamineController {

    @Autowired
    ExamineService examineService;


    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("/examine/index");
    }

    @RequestMapping("toList")
    public PageInfo<Examine> selectAll(@RequestBody Map<String,Object> params){
        return examineService.selectAll(params);
    }
    @RequestMapping("toUpdate")
    @ResponseBody
    public Examine toUpdate(Long id){
        return examineService.selectByPrimaryKey(id);
    }

}