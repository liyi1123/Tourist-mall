package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.VisaNation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * InnoDB free: 9216 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-06
 */
public interface VisaNationMapper extends BaseMapper<VisaNation> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();


    /**
     * 获取所有地区
     */
    List<VisaNation> list(@Param("condition") int parentId);
}
