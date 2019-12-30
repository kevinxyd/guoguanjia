package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.WorkOrder;
import com.xyd.service.WorkOrderService;
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
 * @Date: 2019/12/27/15:44
 * @Description:
 */
@RestController
@RequestMapping("manager/admin/work")
public class WorkOrderController {


    @Autowired
    WorkOrderService workOrderService;

    @RequestMapping("")
    public ModelAndView toWork(){
        return new ModelAndView("/work/admin/work");
    }
    //详情页面
    @RequestMapping("toDetail")
    public ModelAndView toDetail(){
        return new ModelAndView("/work/work-detail");
    }
    @RequestMapping("toPrint")
    public ModelAndView toPrint(){
        return new ModelAndView("/work/print");
    }

    @RequestMapping("toList")
    public PageInfo<WorkOrder> selectAll(@RequestBody Map<String,Object> params){
        return workOrderService.selectAll(params);
    }
    //详情页面数据查询
    @RequestMapping("selectByOid")
    public Map<String,Object> selectByOid(long oid){
        return workOrderService.selectByOid(oid);
    }
}