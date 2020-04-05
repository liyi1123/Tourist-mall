package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.modular.system.model.ProductType;
import com.stylefeng.guns.modular.system.dao.ProductTypeMapper;
import com.stylefeng.guns.modular.travel.service.IProductTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 7168 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-16
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> list(String condition) {
        return productTypeMapper.list(condition);
    }

    @Override
    public ProductType select(Integer productTypeId) {
        return productTypeMapper.select(productTypeId);
    }
}
