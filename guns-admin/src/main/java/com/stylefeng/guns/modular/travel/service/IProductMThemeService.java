package com.stylefeng.guns.modular.travel.service;

import com.stylefeng.guns.modular.system.model.ProductMTheme;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-30
 */
public interface IProductMThemeService extends IService<ProductMTheme> {

    List<Integer> getThemeIdsByProductIdAndType(Integer scenicSpotId,String type);
}
