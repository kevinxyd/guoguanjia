package com.xyd.mapper;

import com.xyd.entity.SysLog;
import com.xyd.mapper.provider.SysLogProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysLogMapper extends Mapper<SysLog> {
    @SelectProvider(type = SysLogProvider.class,method ="selectByCondition" )
    List<SysLog> selectByCondition(Map<String, Object> params);
}