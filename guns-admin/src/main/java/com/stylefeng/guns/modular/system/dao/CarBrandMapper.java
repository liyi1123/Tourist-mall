package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.CarBrand;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * InnoDB free: 11264 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-01
 */
public interface CarBrandMapper extends BaseMapper<CarBrand> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 查询汽车品牌
     * @param parentId
     * @return
     */
    List<CarBrand> select(@Param("parentId") Integer parentId);

}
