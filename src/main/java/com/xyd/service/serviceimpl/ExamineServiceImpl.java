package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.Examine;
import com.xyd.mapper.ExamineMapper;
import com.xyd.service.ExamineService;
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
 * @Date: 2019/12/26/15:13
 * @Description:
 */
@Service
@Transactional
public class ExamineServiceImpl extends IServiceImpl<Examine> implements ExamineService {
    @Autowired
    ExamineMapper examineMapper;

    @Override
    public PageInfo<Examine> selectAll(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));

        List<Examine> examines = examineMapper.selectByCondition(params);

        PageInfo<Examine> pageInfo = new PageInfo<>(examines);//生成分页对象

        return pageInfo;
    }
}