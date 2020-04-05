package com.stylefeng.guns.modular.travel.service;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.ProductType;
import com.stylefeng.guns.modular.system.model.VisaNation;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-06
 */
public interface IVisaNationService extends IService<VisaNation> {
    /**
     * 获取树节点
     */
    List<ZTreeNode> tree();

    List<VisaNation> list(@Param("condition") int parentId);
}
