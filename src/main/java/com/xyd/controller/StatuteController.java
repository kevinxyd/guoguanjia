package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Result;
import com.xyd.entity.Statute;
import com.xyd.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/30/16:35
 * @Description:
 */
@RestController
@RequestMapping("manager/statute")
public class StatuteController {

    @Autowired
    StatuteService statuteService;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/statute/index");
    }
    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/statute/update");
    }

    @RequestMapping("toList")
    public PageInfo<Statute> selectByCondition(@RequestBody Map<String,Object> params){
        return statuteService.selectByCondition(params);
    }
    @RequestMapping("toUpdate")

    public Statute toUpdate(Long id){
        return statuteService.selectByPrimaryKey(id);
    }

    @RequestMapping("update")
    public Result update(@RequestBody Statute statute){
        statute.setUpdateDate(new Date());
        int i = statuteService.updateByPrimaryKeySelective(statute);
        Result result = new Result();
        if(i>0){
            result.setMsg("操作成功");
            result.setSuccess(true);
        }
        return result;

    }
    @RequestMapping("insert")
    public Result insert(@RequestBody Statute statute){
        statute.setPubDate(new Date());
        statute.setCreateDate(new Date());
        statute.setUpdateDate(new Date());
        statute.setDelFlag("0");
        int i = statuteService.insertSelective(statute);
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
        int i = statuteService.deleteByPrimaryKey(id);
        Result result = new Result();
        if (i >0 ){
            result.setSuccess(true);
            result.setMsg("已删除");
        }
        return result;
    }

}