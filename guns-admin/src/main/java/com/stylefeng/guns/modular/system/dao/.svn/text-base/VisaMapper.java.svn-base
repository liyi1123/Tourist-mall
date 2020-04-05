package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Visa;
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
public interface VisaMapper extends BaseMapper<Visa> {

    /**
     * 获取所有签证信息列表
     */
    List<Map<String,Object>> list(@Param("condition") String condition);

}
