package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Statute;

import java.util.Map;

public interface StatuteService extends IService<Statute> {

    PageInfo<Statute> selectByCondition(Map<String,Object> condition);
}
