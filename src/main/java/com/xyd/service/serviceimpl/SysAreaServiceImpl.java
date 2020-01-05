package com.xyd.service.serviceimpl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.Statute;
import com.xyd.entity.SysArea;
import com.xyd.entity.SysAreaListener;
import com.xyd.mapper.SysAreaMapper;
import com.xyd.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
@Transactional

public class SysAreaServiceImpl extends IServiceImpl<SysArea> implements SysAreaService {

    @Autowired
    SysAreaMapper sysAreaMapper;


    @Override
    public PageInfo<SysArea> selectByPage(Map<String, Object> condition) {
        //默认值设置
        if(StringUtils.isEmpty(condition.get("pageNum"))){
            condition.put("pageNum",1);
        }
        if(StringUtils.isEmpty(condition.get("pageSize"))){
            condition.put("pageSize",5);
        }
        PageInfo<SysArea> pageInfo = null;
        PageHelper.startPage((Integer) condition.get("pageNum"),(Integer) condition.get("pageSize"));
        //根据父区域id的查询
        if(condition.containsKey("aid")&&!StringUtils.isEmpty(condition.get("aid"))){
            int id =  (Integer) condition.get("aid");
            List<SysArea> list = sysAreaMapper.selectByPid((long) id);
            pageInfo = new PageInfo<>(list);
        }
        return pageInfo;

    }

    /**
     * 根据传入的输出流对象，进行excel写入操作，并返回该输出流对象
     * @param outputStream
     * @return
     */
    @Override
    public OutputStream writeExcel(OutputStream outputStream){

        //获取excel写出对象
        ExcelWriter writer = EasyExcel.write(outputStream, SysArea.class).build();
        //获取sheet对象
        WriteSheet sheet = EasyExcel.writerSheet("sysarea数据").build();
        List<SysArea> sysAreas = mapper.selectAll();
        writer.write(sysAreas,sheet);//将数据写出到excel表的对应sheet位置

        //关闭资源
        writer.finish();
        return outputStream;
    }

    /**
     * 将传入的excel文件的组成的inputStream流读取，转换成java对象，并且进行批量插入到数据库
     * @param inputStream
     * @return
     */
    @Override
    public int readExcel(InputStream inputStream){
        int result = 0;
        ExcelReader excelReader = EasyExcel.read(inputStream,
                SysArea.class, new SysAreaListener(sysAreaMapper)).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        excelReader.read(sheet);//读资源
        excelReader.finish();
        result+=1;
        return result;
    }
    @Override
    public SysArea selectByAid(long id) {
        SysArea sysArea = sysAreaMapper.selectByAid(id);
        sysArea.setOldParentIds(sysArea.getParentIds());//给旧parentIds绑定数据
        return sysArea;
    }

    @Override
    public int updateArea(SysArea sysArea) {
        int i = 0;
        i += sysAreaMapper.updateByPrimaryKey(sysArea);

        if(!sysArea.getOldParentIds().equals(sysArea.getParentIds())){
            i+= sysAreaMapper.updateParentIds(sysArea);//更新所有的子区域的parentIds
        }
        return i;
    }
}
