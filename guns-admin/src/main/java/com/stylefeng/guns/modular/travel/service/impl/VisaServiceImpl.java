package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.modular.system.model.Visa;
import com.stylefeng.guns.modular.system.dao.VisaMapper;
import com.stylefeng.guns.modular.travel.service.IVisaService;
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
public class VisaServiceImpl extends ServiceImpl<VisaMapper, Visa> implements IVisaService {

    @Autowired
    private VisaMapper visaMapper;

    @Override
    public List<Map<String, Object>> list(String condition) {
        return visaMapper.list(condition);
    }
}
