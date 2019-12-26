package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Examine;

import java.util.Map;

public interface ExamineService extends IService<Examine> {

//    List<AppVersion> selectAll();

    PageInfo<Examine> selectAll(Map<String, Object> params);
}
