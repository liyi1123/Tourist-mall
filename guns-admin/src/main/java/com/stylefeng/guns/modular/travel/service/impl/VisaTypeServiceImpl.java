package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.VisaType;
import com.stylefeng.guns.modular.system.dao.VisaTypeMapper;
import com.stylefeng.guns.modular.travel.service.IVisaTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-06
 */
@Service
public class VisaTypeServiceImpl extends ServiceImpl<VisaTypeMapper, VisaType> implements IVisaTypeService {

    @Autowired
    private VisaTypeMapper visaTypeMapper;


    @Override
    public List<Map<String, Object>> list(String condition) {
        return visaTypeMapper.list(condition);
    }


}
