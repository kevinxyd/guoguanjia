package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.WorkOrder;

import java.util.Map;

public interface WorkOrderService extends IService<WorkOrder> {
    PageInfo<WorkOrder> selectAll(Map<String, Object> params);


    //根据workOrder的id查询一个详单信息
    Map<String,Object> selectByOid(long oid);
}
