package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysRole;
import com.xyd.mapper.SysRoleMapper;
import com.xyd.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysRoleServiceImpl extends IServiceImpl<SysRole> implements SysRoleService {


    @Autowired
    SysRoleMapper roleMapper;


    @Override
    public PageInfo<SysRole> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));

        List<SysRole> sysUsers = roleMapper.selectByCondition(params);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysUsers);//生成分页对象

        return pageInfo;
    }

    @Override
    public SysRole selectOneByCondition(long id) {
        return roleMapper.selectOneByCondition(id);
    }


    @Override
    public int deleteBatch(long rid, long[] uids){
        return roleMapper.deleteBatch(rid,uids);
    }


    @Override
    public int insertBatch( List<Long> cids,long rid){
        return roleMapper.insertBatch(cids,rid);
    }
}
