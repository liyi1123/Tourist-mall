package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.VisaType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 9216 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-06
 */
public interface VisaTypeMapper extends BaseMapper<VisaType> {

    /**
     * 获取所有景点列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);


}
