package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.AppVersion;
import com.xyd.entity.Demand;
import com.xyd.entity.Result;
import com.xyd.service.AppVersionService;
import com.xyd.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/23/19:02
 * @Description:
 */
@RestController
@RequestMapping("/manager/demand")
public class DemandController {

    @Autowired
    DemandService demandService;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/demand/index");
    }

    @RequestMapping("toDetailPage")
    public ModelAndView toDetailPage(){
        return new ModelAndView("/demand/detail");
    }
    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/demand/update");
    }
    @RequestMapping("toList")
    public PageInfo<Demand> selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "8") Integer pageSize){
        return demandService.selectPage(pageNum,pageSize);
    }
    @RequestMapping("toDetail")
    public Demand toUpdate(Integer id){
        return demandService.selectByPrimaryKey(id);
    }
    //修改
    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Demand demand) {
        demand.setUpdateDate(new Date());
        Result result = new Result();
        int i = demandService.updateByPrimaryKeySelective(demand);
        if (i > 0){
            result.setSuccess(true);
            result.setMsg("修改成功");
        }
        return result;
    }
   /* @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/app/update");
    }




    //根据id查询
    @RequestMapping("toUpdate")
    public AppVersion toUpdate(Integer id){
        return appVersionService.selectByPrimaryKey(id);
    }
    //修改
    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody AppVersion appVersion) {

        appVersion.setUpdateDate(new Date());
        Result result = new Result();
        int i = appVersionService.updateByPrimaryKeySelective(appVersion);
        if (i > 0){
            result.setSuccess(true);
            result.setMsg("修改成功");
        }
        return result;
    }
    //添加
    @RequestMapping("insert")
    public Result insert(@RequestBody AppVersion appVersion){
        appVersion.setUpdateDate(new Date());
        appVersion.setCreateDate(new Date());
        appVersion.setDelFlag("0");
        int i = appVersionService.insertSelective(appVersion);
        Result result = new Result();
        if (i > 0){
            result.setSuccess(true);
            result.setMsg("添加成功");
        }
        return result;
    }
    //根据id删除
    @RequestMapping("delete")
    public Result delete(Long id){
        int i = appVersionService.deleteByPrimaryKey(id);
        Result result = new Result();
        if (i >0 ){
            result.setSuccess(true);
            result.setMsg("已删除");
        }
        return result;
    }*/

}