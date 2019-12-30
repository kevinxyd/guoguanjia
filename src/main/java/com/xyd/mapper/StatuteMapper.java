package com.xyd.mapper;

import com.xyd.entity.Statute;
import com.xyd.mapper.provider.StatuteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface StatuteMapper extends Mapper<Statute> {

    @SelectProvider(type = StatuteProvider.class,method = "selectByCondition")
    List<Statute> selectByCondition(Map<String,Object> map);
}
