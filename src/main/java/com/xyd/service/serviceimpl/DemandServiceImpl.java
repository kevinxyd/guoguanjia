package com.xyd.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyd.entity.AppVersion;
import com.xyd.entity.Demand;
import com.xyd.service.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/24/16:41
 * @Description:
 */
@Service
@Transactional
public class DemandServiceImpl extends IServiceImpl<Demand> implements DemandService {
    @Override
    public PageInfo<Demand> selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Demand> list = mapper.selectAll();//当前方法返回值已经被替换成Page对象类型
        return new PageInfo<>(list);
    }
}