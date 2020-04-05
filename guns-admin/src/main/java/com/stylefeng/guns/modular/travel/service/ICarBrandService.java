package com.stylefeng.guns.modular.travel.service;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.CarBrand;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-01
 */
public interface ICarBrandService extends IService<CarBrand> {

    /**
     * 获取树节点
     */
    List<ZTreeNode> tree();

    /**
     * 查询汽车品牌
     * @param parentId
     * @return
     */
    List<CarBrand> select(@Param("parentId") Integer parentId);

}
