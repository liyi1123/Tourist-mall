package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.ProductType;
import com.stylefeng.guns.modular.system.model.VisaNation;
import com.stylefeng.guns.modular.system.dao.VisaNationMapper;
import com.stylefeng.guns.modular.travel.service.IVisaNationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-06
 */
@Service
public class VisaNationServiceImpl extends ServiceImpl<VisaNationMapper, VisaNation> implements IVisaNationService {

    @Autowired
    private VisaNationMapper visaNationMapper;

    @Override
    public List<ZTreeNode> tree() {
        return visaNationMapper.tree();
    }

    @Override
    public List<VisaNation> list(int parentId) {
        return visaNationMapper.list(parentId);
    }
}
