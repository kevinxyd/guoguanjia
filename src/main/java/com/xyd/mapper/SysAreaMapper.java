package com.xyd.mapper;

import com.xyd.entity.SysArea;
import com.xyd.mapper.provider.SysAreaProvider;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysAreaMapper extends Mapper<SysArea> {


    @InsertProvider(type = SysAreaProvider.class,method = "insertBath")
    int insertBath(@Param("areas") List<SysArea> areas);

    @Select("select sub.*,parent.name parentName from  sys_area sub,sys_area parent where  sub.parent_ids like CONCAT('%',#{aid},'%') and  sub.parent_id=parent.id and sub.del_flag = 0")
    List<SysArea> selectByPid(long aid);


    @Select("select  " +
            " sub.*,parent.name parentName " +
            "from " +
            " sys_area sub,sys_area parent " +
            "where " +
            " sub.parent_id=parent.id " +
            "and " +
            " sub.id=#{id} and sub.del_flag = 0")
    SysArea selectByAid(long id);

    /**
     * 根据父区域更新所有的子区域的parentIds
     * @param sysArea
     * @return
     */
    @Update("update " +
            " sys_area " +
            "set " +
            " parent_ids=REPLACE(parent_ids,#{oldParentIds},#{parentIds}) " +
            "where " +
            " parent_ids like concat('%,',#{id},',%') ")
    int updateParentIds(SysArea sysArea);
}