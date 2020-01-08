package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Result;
import com.xyd.entity.SysRole;
import com.xyd.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2020/01/08/15:20
 * @Description:
 */
@RestController
@RequestMapping("manager/role")
public class SysRoleController {

    @Autowired
    SysRoleService service;



    @RequestMapping("list")
    public List<SysRole> list (){
        return service.selectAll();
    }

    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/role/role");
    }

    @RequestMapping("selectPage")
    public PageInfo<SysRole> selectPage(@RequestBody Map<String,Object> params){
        return service.selectByCondition(params);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdate(){
        return new ModelAndView("/role/role-save");
    }


    @RequestMapping("toDetailPage")
    public ModelAndView toDetailPage(){
        return new ModelAndView("/role/role-detail");
    }

    @RequestMapping("toRoleUser")
    public ModelAndView toRoleUser(){
        return new ModelAndView("/role/role-user");
    }

    @RequestMapping("detail")
    public SysRole detail(long id){
        return service.selectOneByCondition(id);
    }

    @RequestMapping("deleteBatch")
    public Result deleteBatch(@RequestBody Map<String,Object>params){
        long rid = params.containsKey("rid")? (int)params.get("rid")  :0;
        List<Integer> list = params.containsKey("uids")? (List<Integer>) params.get("uids") :null;
        long[] uids = null;
        if(list!=null) {
            uids  = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                //Integer.valueOf(list.get(i)) 转成int
                uids[i] = Integer.valueOf(list.get(i));
            }
        }
        Result result = new Result();
        if (service.deleteBatch(rid,uids)>0){
            result.setMsg("移除人员成功");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("deleteBatch2")
    public Result deleteBatch2(long rid,long[] uids){
        Result result = new Result();
        if (service.deleteBatch(rid,uids)>0){
            result.setMsg("移除人员成功");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("insertBatch")
    public Result insertBatch(long rid,Long[] cids){
        List<Long> list = new ArrayList<Long>();
        //Arrays.asList(cids):   将传入的数组转换成list集合  只支持非基本类型数组转换集合list
        //所以接收方法参数需要设置成包装类的数组Long[]
        int i = service.insertBatch(Arrays.asList(cids), rid);
        Result result = new Result();
        if(i>0){
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }
}