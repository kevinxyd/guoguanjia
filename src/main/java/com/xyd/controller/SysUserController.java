package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysUser;
import com.xyd.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2020/01/06/17:09
 * @Description:
 */
@RestController
@RequestMapping("manager/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("")
    public ModelAndView index(){
        return new ModelAndView("/user/user-list");
    }
    @RequestMapping("toList")
    public PageInfo<SysUser> selectAll(@RequestBody Map<String,Object> params){
        return sysUserService.selectByCondition(params);
    }
}