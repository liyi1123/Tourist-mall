package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Hotel;
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
 * @since 2018-07-30
 */
public interface HotelMapper extends BaseMapper<Hotel> {

    /**
     * 获取所有酒店
     */
    List<Map<String, Object>> list(@Param("condition") String condition);

    /**
     *获取酒店类型列表
     */
    List<Hotel> listHotel(@Param("productTypeId") Integer productTypeId,@Param("hotelId") Integer hotelId);

    /**
     * 获取热门酒店列表(hotel的hot>3)
     */
    List<Map<String,Object>> hotelList(@Param("page") Page<Hotel> page, @Param("productTypeId") Integer productTypeId ,
                                       @Param("gradeId") Integer gradeId ,@Param("regionId") Integer regionId,@Param("brandId") Integer brandId);

    /**
     * 根据hotelId查询对象
     */
    Map<String,Object> hotelDetail(@Param("hotelId") Integer hotelId);
}
