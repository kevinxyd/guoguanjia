package com.xyd.service.serviceimpl;

import com.xyd.entity.Waste;
import com.xyd.service.WasteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WasteServiceImpl extends IServiceImpl<Waste> implements WasteService {


}
