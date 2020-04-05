package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.CarBrand;
import com.stylefeng.guns.modular.system.dao.CarBrandMapper;
import com.stylefeng.guns.modular.travel.service.ICarBrandService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-01
 */
@Service
public class CarBrandServiceImpl extends ServiceImpl<CarBrandMapper, CarBrand> implements ICarBrandService {

    @Autowired
    private CarBrandMapper carBrandMapper;

    public List<ZTreeNode> tree(){
        return carBrandMapper.tree();
    }

    @Override
    public List<CarBrand> select(Integer parentId) {
        return carBrandMapper.select(parentId);
    }

}
