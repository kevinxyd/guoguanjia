package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.SysOffice;
import com.xyd.mapper.SysOfficeMapper;
import com.xyd.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
//@CacheConfig(cacheNames = "sysOfficeCache")
public class SysOfficeServiceImpl extends IServiceImpl<SysOffice> implements SysOfficeService{

    @Autowired
    SysOfficeMapper officeMapper;

    @Override
    public PageInfo<SysOffice> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        SysOfficeMapper sysOfficeMapper= (SysOfficeMapper) mapper;
        List<SysOffice> sysOffices = sysOfficeMapper.selectByCondition(params);

        PageInfo<SysOffice> pageInfo = new PageInfo<>(sysOffices);//生成分页对象

        return pageInfo;
    }


    @Override
    public SysOffice selectByOid(long oid){
        SysOfficeMapper sysOfficeMapper= (SysOfficeMapper) mapper;
        return sysOfficeMapper.selectByOid(oid);
    }

    //@Cacheable(/*cacheNames = "sysOfficeCache",*/key = "'com.xyd.service.serviceimpl.SysOfficeServiceImpl:selectAll'")
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
    //@CacheEvict(/*cacheNames = "sysOfficeCache" ,*/allEntries = true)
    @Override
    public int updateByPrimaryKeySelective(SysOffice record) {
        return super.updateByPrimaryKeySelective(record);
    }


    /**
     * 更新office和  office_waste
     * @param sysOffice
     * @return
     */
    @Override

    public int update(SysOffice sysOffice){
        long[] wasteIds = null;
        int result = 0;

        mapper.updateByPrimaryKeySelective(sysOffice);
        result+=1;
        officeMapper.deleteOfficeWaste(sysOffice.getId());
        result += 1;
        if(sysOffice.getWastes().size()>0){
            wasteIds = new long[sysOffice.getWastes().size()];
            for (int i = 0; i < sysOffice.getWastes().size(); i++) {
                wasteIds[i]=sysOffice.getWastes().get(i).getId();
            }
            officeMapper.insertBathOfficeWaste(sysOffice.getId(),wasteIds);
            result +=1;
        }
        return result;
    }

    @Override
    public List<SysOffice> selectByRid(long rid){
        return officeMapper.selectByRid(rid);
    }
}
