package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.ProductType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 7168 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-16
 */
public interface ProductTypeMapper extends BaseMapper<ProductType> {

    /**
     * 获取所有景点
     */
    List<ProductType> list(@Param("condition") String condition);

    /**
     * 根据类型查询单个对象
     */
    ProductType select(@Param("productTypeId") Integer productTypeId);

}
