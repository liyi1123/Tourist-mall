package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Images;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 4096 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-23
 */
public interface ImagesMapper extends BaseMapper<Images> {

    /**
     * 获取照片
     */
    List<Map<String, Object>> list(@Param("status") int status);
}
