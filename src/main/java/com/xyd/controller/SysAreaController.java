package com.xyd.controller;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Result;
import com.xyd.entity.Statute;
import com.xyd.entity.SysArea;
import com.xyd.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manager/area")
public class SysAreaController {

    @Autowired
    SysAreaService sysAreaService;


    /**
     * 跳转到查询页
     * @return
     */
    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/area/area");
    }

    /**
     * Excel下载操作:
     * 1.设置响应头
     * 2.设置文件乱码处理
     * 3.获取响应数据流
     * 4.写出到页面
     */
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {

        response.setHeader("Content-Disposition","attachment;filename=sysArea.xls");
//        FileOutputStream fileOutputStream = new FileOutputStream("");
        OutputStream outputStream = response.getOutputStream();
        outputStream = sysAreaService.writeExcel(outputStream);//响应流数据已经有文件信息
        MultipartFile file;
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile upFile) throws IOException {
        Result result = new Result();
        int i = sysAreaService.readExcel(upFile.getInputStream());
        if(i>0){
            result.setMsg("导入数据成功");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("selectPage")
    public PageInfo<SysArea> selectByPage(@RequestBody Map<String,Object> params){
        return sysAreaService.selectByPage(params);
    }

    @RequestMapping("selectAll")
    public List<SysArea> selectAll(){
        return  sysAreaService.selectAll();
    }

    @RequestMapping("toUpdate")
    public SysArea toUpdate(long id){
        return sysAreaService.selectByAid(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/area/save");
    }

    @RequestMapping("awesome")
    public ModelAndView awesome(){
        return new ModelAndView("/modules/font-awesome");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysArea area){
        Result result = new Result();

        int i = sysAreaService.updateArea(area);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }
    //根据id删除
    @RequestMapping("delete")
    public Result delete(Long id){
        SysArea sysArea = new SysArea();
        sysArea.setId(id);
        sysArea.setDelFlag("1");
        int i = sysAreaService.updateByPrimaryKeySelective(sysArea);
        Result result = new Result();
        if (i >0 ){
            result.setSuccess(true);
            result.setMsg("已删除");
        }
        return result;
    }



}
