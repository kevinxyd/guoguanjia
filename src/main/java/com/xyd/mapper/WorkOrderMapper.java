package com.xyd.mapper;

import com.xyd.entity.WorkOrder;
import com.xyd.mapper.provider.WorkOrderProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface WorkOrderMapper extends Mapper<WorkOrder> {

    @SelectProvider(type = WorkOrderProvider.class,method ="selectByCondition" )
    List<WorkOrder> selectByCondition(Map<String, Object> params);
}