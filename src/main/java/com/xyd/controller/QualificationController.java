package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.AppVersion;
import com.xyd.entity.Qualification;
import com.xyd.entity.Result;
import com.xyd.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/25/15:58
 * @Description:
 */
@RestController
@RequestMapping("manager/qualification")
public class QualificationController {

    @Autowired
    QualificationService qualificationService;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/qualification/index");
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/qualification/update");
    }



    @RequestMapping("toList")
    public PageInfo<Qualification> selectAll(@RequestBody Map<String,Object> params){
        return qualificationService.selectByCondition(params);
    }

    @RequestMapping("toUpdate")
    public Map<String,Object> toUpdate(Long id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("qu",qualificationService.selectByPrimaryKey(id));
        map.put("oid",qualificationService.selectOfficeId(id));
        return map;
    }
    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Qualification qualification) {

        qualification.setUpdateDate(new Date());
        Result result = new Result();
        int i = qualificationService.updateByPrimaryKeySelective(qualification);
        if (i > 0){
            result.setSuccess(true);
            result.setMsg("已审核");
        }
        return result;
    }
}