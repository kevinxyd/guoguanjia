package com.xyd.mapper;

import com.xyd.entity.Examine;
import com.xyd.entity.Qualification;
import com.xyd.mapper.provider.ExamineProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ExamineMapper extends Mapper<Examine> {

    @SelectProvider(type = ExamineProvider.class,method = "selectByCondition")
    List<Examine> selectByCondition( Map<String, Object> condition);

}