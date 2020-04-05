package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Region;
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
public interface RegionMapper extends BaseMapper<Region> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();


    Region selectRegionById(@Param("id") Integer id);



}
