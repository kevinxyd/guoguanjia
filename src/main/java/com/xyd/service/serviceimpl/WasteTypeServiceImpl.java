package com.xyd.service.serviceimpl;

import com.xyd.entity.WasteType;
import com.xyd.service.WasteTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WasteTypeServiceImpl extends IServiceImpl<WasteType> implements WasteTypeService {


}
