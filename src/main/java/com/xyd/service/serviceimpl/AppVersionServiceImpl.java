package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.AppVersion;
import com.xyd.mapper.AppVersionMapper;
import com.xyd.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/23/18:36
 * @Description:
 */
@Service
@Transactional
//@CacheConfig(cacheNames = "appVersionCache")
public class AppVersionServiceImpl extends IServiceImpl<AppVersion> implements AppVersionService {


    //@Cacheable(key = "'AppVersionServiceImpl:selectByCondition'+#pageNum+#pageSize")
    @Override
    public PageInfo<AppVersion> selectPage(Integer pageNum, Integer pageSize) {
        //开启分页拦截  分页插件会自动在最近一个sql执行前，自动添加分页的sql代码 limit x,x
        PageHelper.startPage(pageNum,pageSize);
        List<AppVersion> list = mapper.selectAll();//当前方法返回值已经被替换成Page对象类型
        return new PageInfo<>(list);
    }

    //@CacheEvict(allEntries = true)
    @Override
    public int updateByPrimaryKeySelective(AppVersion T) {
        return super.updateByPrimaryKeySelective(T);
    }

    /*@Autowired
    AppVersionMapper appVersionMapper;
    @Override
    public int insert(AppVersion T) {
        return appVersionMapper.insert(T);
    }*/

}