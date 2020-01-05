package com.xyd.service;



import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysArea;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface SysAreaService extends  IService<SysArea>{
    OutputStream writeExcel(OutputStream outputStream);

    int readExcel(InputStream inputStream);
    PageInfo<SysArea> selectByPage(Map<String, Object> params);

    SysArea selectByAid(long id);

    int updateArea(SysArea sysArea);



}
