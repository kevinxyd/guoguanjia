package com.xyd.service;


import com.xyd.entity.SysResource;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {


    List<SysResource> selectByRid(long rid);

    List<SysResource> selectByUid(long uid);
}
