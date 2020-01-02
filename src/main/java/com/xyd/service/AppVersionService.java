package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.AppVersion;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/23/18:24
 * @Description:
 */
public interface AppVersionService extends IService<AppVersion> {

    PageInfo<AppVersion> selectPage(Integer pageNum, Integer pageSize);
}