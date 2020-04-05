package com.stylefeng.guns.modular.travel.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.RentCar;
import com.stylefeng.guns.modular.system.dao.RentCarMapper;
import com.stylefeng.guns.modular.travel.service.IRentCarService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-02
 */
@Service
public class RentCarServiceImpl extends ServiceImpl<RentCarMapper, RentCar> implements IRentCarService {

    @Autowired
    private RentCarMapper rentCarMapper;

    @Override
    public List<Map<String, Object>> list(String condition) {
        return rentCarMapper.list(condition);
    }

    @Override
    public List<RentCar> rentCar(Integer productTypeId) {
        return rentCarMapper.rentCar(productTypeId);
    }

    @Override
    public List<Map<String, Object>> rentCarList(Page<RentCar> page, Integer productTypeId, Integer carBrandId, Integer seatCount) {
        return rentCarMapper.rentCarList(page,productTypeId,carBrandId,seatCount);
    }
}
