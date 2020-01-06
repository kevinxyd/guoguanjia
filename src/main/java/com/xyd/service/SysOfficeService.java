package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysOffice;

import java.util.Map;

public interface SysOfficeService extends IService<SysOffice> {


    PageInfo<SysOffice> selectByCondition(Map<String, Object> params);

    SysOffice selectByOid(long oid);


    int update(SysOffice sysOffice);
}
