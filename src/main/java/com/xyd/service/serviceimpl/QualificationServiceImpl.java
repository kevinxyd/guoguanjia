package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.Qualification;
import com.xyd.mapper.QualificationMapper;
import com.xyd.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/25/15:24
 * @Description:
 */
@Service
@Transactional
public class QualificationServiceImpl extends IServiceImpl<Qualification> implements QualificationService {

    @Autowired
    QualificationMapper qualificationMapper;

    @Override
    public PageInfo<Qualification> selectByCondition(Map<String, Object> params) {
        //对pageNum、pageSize进行判断   给默认值
        if(!params.containsKey("pageNum")|| StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }

        if(!params.containsKey("pageSize")|| StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<Qualification> qualificationList = qualificationMapper.selectByCondition(params);
        return new PageInfo<>(qualificationList);
    }

    @Override
    public long selectOfficeId(long qid) {
        return qualificationMapper.selectOfficeId(qid);
    }
}