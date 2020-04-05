package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.modular.system.model.ProductMTheme;
import com.stylefeng.guns.modular.system.dao.ProductMThemeMapper;
import com.stylefeng.guns.modular.travel.service.IProductMThemeService;
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
 * @since 2018-07-30
 */
@Service
public class ProductMThemeServiceImpl extends ServiceImpl<ProductMThemeMapper, ProductMTheme> implements IProductMThemeService {

    @Autowired
    private ProductMThemeMapper productMThemeMapper;


    @Override
    public List<Integer> getThemeIdsByProductIdAndType(Integer scenicSpotId, String type) {
        return productMThemeMapper.getThemeIdsByProductIdAndType(scenicSpotId,type);
    }
}
