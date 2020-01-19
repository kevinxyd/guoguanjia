package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysLog;
import com.xyd.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/syslog")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;





    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/log/log");
    }

    @RequestMapping("selectPage")
    public PageInfo<SysLog> selectPage(@RequestBody Map<String,Object> params){
        return sysLogService.selectByCondition(params);
    }

    @RequestMapping("toDetail")
    public SysLog toDetail(Integer id){
        return sysLogService.selectByPrimaryKey(id);
    }

    @RequestMapping("toDetailPage")
    public ModelAndView toDetailPage(){
        return new ModelAndView("/log/log-detail");
    }




}
