package com.xyd.service.serviceimpl;

import com.xyd.entity.SysOffice;
import com.xyd.service.SysOfficeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "sysOfficeCache")
public class SysOfficeServiceImpl extends IServiceImpl<SysOffice> implements SysOfficeService{

    @Cacheable(/*cacheNames = "sysOfficeCache",*/key = "'com.xyd.service.serviceimpl.SysOfficeServiceImpl:selectAll'")
    @Override
    public List<SysOffice> selectAll() {
        return super.selectAll();
    }



    /**
     * 当发生增删改需要清空所有管理的缓存信息
     * CacheEvict:清除缓存信息
     *      allEntries：表示清空所有当前缓存管理对象的key
     * @param record
     * @return
     */
    @CacheEvict(/*cacheNames = "sysOfficeCache" ,*/allEntries = true)
    @Override
    public int updateByPrimaryKeySelective(SysOffice record) {
        return super.updateByPrimaryKeySelective(record);
    }
}
