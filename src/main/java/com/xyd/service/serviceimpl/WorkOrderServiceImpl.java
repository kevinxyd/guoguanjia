package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.Detail;
import com.xyd.entity.SysUser;
import com.xyd.entity.Transfer;
import com.xyd.entity.WorkOrder;
import com.xyd.mapper.DetailMapper;
import com.xyd.mapper.SysUserMapper;
import com.xyd.mapper.TransferMapper;
import com.xyd.mapper.WorkOrderMapper;
import com.xyd.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/27/15:59
 * @Description:
 */
@Service
@Transactional
public class WorkOrderServiceImpl extends IServiceImpl<WorkOrder> implements WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;


    @Autowired
    SysUserMapper userMapper;

    @Autowired
    DetailMapper detailMapper;

    @Autowired
    TransferMapper transferMapper;

    //分页查询
    @Override
    public PageInfo<WorkOrder> selectAll(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));

        List<WorkOrder> WorkOrders = workOrderMapper.selectByCondition(params);

        PageInfo<WorkOrder> pageInfo = new PageInfo<>(WorkOrders);//生成分页对象

        return pageInfo;
    }

    //订单详情查询
    @Override
    public Map<String, Object> selectByOid(long oid) {
        //根据oid查询 workOrder信息
        WorkOrder workOrder = mapper.selectByPrimaryKey(oid);
        //根据oid查询创建用户
        SysUser createUser = userMapper.selectById(workOrder.getCreateUserId());


        SysUser transportUser = null;
        if(!StringUtils.isEmpty(workOrder.getTransportUserId())){
            //根据oid查询运输用户
            transportUser=  userMapper.selectById(workOrder.getTransportUserId());
        }

        SysUser recipientUser = null;
        if(!StringUtils.isEmpty(workOrder.getRecipientUserId())){
            //根据oid查询处理用户
            recipientUser = userMapper.selectById(workOrder.getRecipientUserId());
        }
        //根据oid查询详单
        List<Detail> details = detailMapper.selectByOid(oid);
        //根据oid查询转运记录
        List<Transfer> transfers = transferMapper.selectByOid(oid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("work",workOrder);
        map.put("createUser",createUser);
        map.put("transportUser",transportUser);
        map.put("recipientUser",recipientUser);
        map.put("details",details);
        map.put("transfers",transfers);

        return map;
    }
}