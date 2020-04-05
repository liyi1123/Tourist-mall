package com.stylefeng.guns.modular.travel.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.ScenicSpot;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-17
 */
public interface IScenicSpotService extends IService<ScenicSpot> {

    /**
     * 获取所有景点列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);

    int addScenicSpot(ScenicSpot scenicSpot);

    /**
     * 获取所有景点类型的列表
     */
    List<ScenicSpot> listScenic(@Param("productTypeId") Integer productTypeId);

    /**
     * 获取景点id所对应的列表
     */
    Map<String,Object> select(@Param("scenicSpotId") Integer scenicSpotId);

    /**
     * 获取所有景点类型的列表详情
     * @param productTypeId
     * @return
     */
    List<Map<String,Object>> scenicTypeList(@Param("productTypeId") Integer productTypeId);


    List<Map<String,Object>> scenicSpotList(Page<ScenicSpot> page,Integer productTypeId,Integer regionId);


   /* Integer scenicSpotListCount(@Param("page") Page<ScenicSpot> page,@Param("productTypeId") Integer productTypeId);*/


}
