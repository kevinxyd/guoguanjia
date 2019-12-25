package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Qualification;

import java.util.Map;

public interface QualificationService extends IService<Qualification> {

    PageInfo<Qualification> selectByCondition(Map<String,Object> params);

    long selectOfficeId(long qid);
}
