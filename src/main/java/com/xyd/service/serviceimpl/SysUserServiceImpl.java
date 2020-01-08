package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysOffice;
import com.xyd.entity.SysRole;
import com.xyd.entity.SysUser;
import com.xyd.mapper.SysRoleMapper;
import com.xyd.mapper.SysUserMapper;
import com.xyd.service.SysUserService;
import com.xyd.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysUserServiceImpl extends IServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysUser> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        SysUserMapper sysUserMapper= (SysUserMapper) mapper;
        List<SysUser> sysUsers = sysUserMapper.selectByCondition(params);

        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);//生成分页对象

        return pageInfo;
    }

    @Override
    public Map<String, Object> selectById(long uid) {
        SysUser sysUser = sysUserMapper.selectById(uid);
        List<SysRole> sysRoles = sysRoleMapper.selectAll();
        HashMap<String, Object> map = new HashMap<>();
        map.put("sysUser",sysUser);
        map.put("sysRoles",sysRoles);
        return map;
    }

    @Override
    public SysUser checkSysUser(SysUser record) {
        record.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(record.getPassword())+record.getUsername()));
        List<SysUser> select = sysUserMapper.select(record);
        if (select.size()>0){
            return select.get(0);
        }
        return null;
    }


    @Override
    public List<SysUser> selectByRid(long rid){
        return sysUserMapper.selectByRid(rid);
    }


    @Override
    public List<SysUser> selectNoRole(long rid, long oid){
        return sysUserMapper.selectNoRole(rid,oid);
    }

}
