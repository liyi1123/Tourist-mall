package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.ScenicSpot;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.swagger.models.auth.In;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-17
 */
public interface ScenicSpotMapper extends BaseMapper<ScenicSpot> {


    /**
     * 获取所有景点列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);

    /**
     * 获取景点id所对应的列表
     */
    Map<String,Object> select(@Param("scenicSpotId") Integer scenicSpotId);

    /**
     * 获取所有景点类型的列表
     */
    List<ScenicSpot> listScenic(@Param("productTypeId") Integer productTypeId);


    /**新增景点 返回新增对象的ID
     * */
    int addScenicSpot(ScenicSpot scenicSpot);


    /**
     * 获取所有景点类型的列表详情
     * @param productTypeId
     * @return
     */
    List<Map<String,Object>> scenicTypeList(@Param("productTypeId") Integer productTypeId);

    /**
     * 获取所有景点列表
     * @param productTypeId 可以为null
     * @return
     */
    List<Map<String,Object>> scenicSpotList(@Param("page") Page<ScenicSpot> page,@Param("productTypeId") Integer productTypeId,
                                            @Param("regionId") Integer regionId);

   /* Integer scenicSpotListCount(@Param("page") Page<ScenicSpot> page,@Param("productTypeId") Integer productTypeId);*/
}
