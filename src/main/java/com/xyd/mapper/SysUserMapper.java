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

    @Select("select su.*,GROUP_CONCAT(sr.name ) roleName," +
            "so.`name` officeName,so.id officeId from sys_user" +
            " su left join sys_user_role sur on su.id = sur.user_id " +
            "left join sys_role sr  on sr.id = sur.role_id left join " +
            "sys_office so on su.office_id = so.id   where su.id = #{uid} " +
            "GROUP BY su.id")
    @Results({

            @Result(property = "id",column = "id"),
            @Result(property = "sysOffice.id",column = "officeId"),
            @Result(property = "sysOffice.name",column = "officeName")
    })
    SysUser selectById(long uid);




    //设置关联查询，将用户id对应的所有的roles查询出来
    @SelectProvider(type = SysUserProvider.class,method = "selectByCondition")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "sysOffice.name",column = "officeName")
    })
    List<SysUser> selectByCondition(Map<String,Object> params);

    /**
     * 根据角色id，查询用户信息
     * @param rid
     * @return
     */
    @Select("select " +
            " su.* " +
            "from " +
            " sys_role sr,sys_user_role sur,sys_user su " +
            "where " +
            " sr.id=#{rid} " +
            "and " +
            " sr.id=sur.role_id " +
            "and " +
            " sur.user_id=su.id")
    List<SysUser> selectByRid(long rid);


    @Select("select " +
            " * " +
            "from " +
            " sys_user " +
            "where " +
            " office_id=#{oid} " +
            "and " +
            " id  " +
            "not in " +
            "( " +
            "select " +
            " sur.user_id " +
            "from " +
            " sys_role sr,sys_user_role sur " +
            "where " +
            " sr.id=#{rid} " +
            "and " +
            " sr.id=sur.role_id " +
            ")")
    List<SysUser> selectNoRole(@Param("rid") long rid,@Param("oid")long oid);
}