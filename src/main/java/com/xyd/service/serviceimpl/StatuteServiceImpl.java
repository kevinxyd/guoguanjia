package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.Statute;
import com.xyd.entity.SysOffice;
import com.xyd.mapper.StatuteMapper;
import com.xyd.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
 * @Date: 2019/12/30/16:30
 * @Description:
 */
@Service
@Transactional
@CacheConfig(cacheNames = "statuteCache")
public class StatuteServiceImpl extends IServiceImpl<Statute> implements StatuteService {

    @Autowired
    StatuteMapper statuteMapper;

    //命中率：正确命中缓存的数据
    @Cacheable(key = "'StatuteServiceImpl:selectByCondition'+#condition['pageNum']+#condition['pageSize']+#condition['type']")
    @Override
    public PageInfo<Statute> selectByCondition(Map<String, Object> condition) {
        //默认值设置
        if(StringUtils.isEmpty(condition.get("pageNum"))){
            condition.put("pageNum",1);
        }
        if(StringUtils.isEmpty(condition.get("pageSize"))){
            condition.put("pageSize",5);
        }
        PageHelper.startPage((Integer) condition.get("pageNum"),(Integer) condition.get("pageSize"));

        List<Statute> statutes = statuteMapper.selectByCondition(condition);

        PageInfo<Statute> pageInfo = new PageInfo<>(statutes);//生成分页对象

        return pageInfo;

    }

    @CacheEvict(/*cacheNames = "statuteCache" ,*/allEntries = true)
    @Override
    public int updateByPrimaryKeySelective(Statute statute) {
        return super.updateByPrimaryKeySelective(statute);
    }
}