package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysOffice;
import com.xyd.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/office")
public class SysOfficeController {

    @Autowired
    SysOfficeService service;


    @RequestMapping("list")
    public List<SysOffice> list (){
        return service.selectAll();
    }

    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/office/office");
    }

    @RequestMapping("selectPage")
    public PageInfo<SysOffice> selectPage(@RequestBody Map<String,Object> params){
        return service.selectByCondition(params);
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(){
        return new ModelAndView("/office/update");
    }

}
