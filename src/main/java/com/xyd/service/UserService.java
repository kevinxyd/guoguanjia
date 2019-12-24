package com.xyd.service;

import com.github.pagehelper.PageInfo;
import com.xyd.entity.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/24/19:24
 * @Description:
 */
public interface UserService extends IService<User> {
    PageInfo<User>  selectPage(int pageNum, int pageSize);
}