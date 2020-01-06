package com.xyd.mapper;

import com.xyd.entity.SysUser;
import com.xyd.mapper.provider.SysUserProvider;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {



    /**
     * 根据用户id查询 用户信息和公司信息
     */
    @Select("select  " +
            " su.*,so.name officeName,so.id officeId " +
            "from " +
            " sys_user su,sys_office so " +
            "where " +
            " su.id=#{uid} " +
            "and " +
            " su.office_id=so.id")
    @Results({
            @Result(property = "id",column = "su.id"),
            @Result(property = "sysOffice.id",column = "officeId"),
            @Result(property = "sysOffice.name",column = "officeName")
    })
    SysUser selectById(long uid);
    //设置关联查询，将用户id对应的所有的roles查询出来
    @SelectProvider(type = SysUserProvider.class,method = "selectByCondition")
    @Results({
            @Result(property = "id",column = "su.id"),
            @Result(property = "sysOffice.name",column = "officeName")
    })
    List<SysUser> selectByCondition(Map<String,Object> params);
}