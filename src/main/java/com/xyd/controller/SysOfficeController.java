package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Result;
import com.xyd.entity.SysOffice;
import com.xyd.entity.Waste;
import com.xyd.entity.WasteType;
import com.xyd.service.SysOfficeService;
import com.xyd.service.WasteService;
import com.xyd.service.WasteTypeService;
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
    SysOfficeService sysOfficeService;

    @Autowired
    WasteService wasteService;

    @Autowired
    WasteTypeService wasteTypeService;

    @RequestMapping("list")
    public List<SysOffice> list (){
        return sysOfficeService.selectAll();
    }

    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/office/office");
    }

    @RequestMapping("selectPage")
    public PageInfo<SysOffice> selectPage(@RequestBody Map<String,Object> params){
        return sysOfficeService.selectByCondition(params);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdate(){
        return new ModelAndView("/office/update");
    }

    @RequestMapping("toUpdate")
    public SysOffice toUpdate(long oid){
        return sysOfficeService.selectByOid(oid);
    }


    @RequestMapping("selectWaste")
    public List<Waste> selectWaste(long selected ){
        Waste waste = new Waste();
        waste.setParentId(selected);
        return wasteService.select(waste);
    }

    @RequestMapping("selectWasteType")
    public List<WasteType> selectWasteType(){
        return wasteTypeService.selectAll();
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysOffice sysOffice){
        int update = sysOfficeService.update(sysOffice);
        Result result = new Result();
        if(update>0){
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }
}
