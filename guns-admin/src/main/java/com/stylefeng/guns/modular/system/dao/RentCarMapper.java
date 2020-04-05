package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.RentCar;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 11264 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-02
 */
public interface RentCarMapper extends BaseMapper<RentCar> {

    /**
     * 获取所有租车信息列表
     */
    List<Map<String,Object>> list(@Param("condition") String condition);

    /**
     * 获取租车类型下最新的前八个租车类型对象栏
     * @param productTypeId
     * @return
     */
    List<RentCar> rentCar(@Param("productTypeId") Integer productTypeId);

    List<Map<String,Object>> rentCarList(@Param("page") Page<RentCar> page, @Param("productTypeId") Integer productTypeId,
                                     @Param("carBrandId") Integer carBrandId,@Param("seatCount") Integer seatCount);


}
