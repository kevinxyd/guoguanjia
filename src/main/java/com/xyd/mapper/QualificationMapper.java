package com.xyd.mapper;

import com.xyd.entity.Qualification;
import com.xyd.mapper.provider.QualificationProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface QualificationMapper extends Mapper<Qualification> {

    @SelectProvider(type = QualificationProvider.class,method ="selectByCondition" )
    List<Qualification> selectByCondition(Map<String,Object> params);

    @Select("select  su.office_id from  qualification qu,sys_user su where  qu.upload_user_id=su.id and  qu.id=#{qid}")
    long selectOfficeId(long qid);
}