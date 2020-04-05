package com.stylefeng.guns.modular.travel.service;

import com.stylefeng.guns.modular.system.model.ProductType;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 7168 kB 服务类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-16
 */
public interface IProductTypeService extends IService<ProductType> {

    /**
     * 获取所有景点
     */
    List<ProductType> list(@Param("condition") String condition);

    /**
     * 根据类型查询单个对象
     */
    ProductType select(@Param("productTypeId") Integer productTypeId);
}
