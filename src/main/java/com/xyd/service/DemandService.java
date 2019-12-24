package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.Demand;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/24/16:40
 * @Description:
 */
public interface DemandService extends IService<Demand> {

    PageInfo<Demand> selectPage(int pageNum, int pageSize);
}