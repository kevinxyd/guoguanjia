package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysLog;

import java.util.Map;

public interface SysLogService extends  IService<SysLog>{
    PageInfo<SysLog> selectByCondition(Map<String, Object> params);
}
