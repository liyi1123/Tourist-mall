package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Region;
import com.stylefeng.guns.modular.system.dao.RegionMapper;
import com.stylefeng.guns.modular.travel.service.IRegionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * InnoDB free: 7168 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-16
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {


    @Autowired
    private RegionMapper regionMapper;
    /**
     * 获取ztree的节点列表
     */
    @Override
    public List<ZTreeNode> tree() {
        return regionMapper.tree();
    }

    @Override
    public Region selectRegionById(Integer id) {
        return regionMapper.selectRegionById(id);
    }
}
