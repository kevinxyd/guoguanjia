package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysOffice;
import com.xyd.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {


    PageInfo<SysUser> selectByCondition(Map<String, Object> params);


    Map<String,Object> selectById(long uid);

    SysUser checkSysUser(SysUser sysUser);


    List<SysUser> selectByRid(long rid);

    List<SysUser> selectNoRole(long rid, long oid);
}
