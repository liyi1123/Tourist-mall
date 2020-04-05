package com.stylefeng.guns.modular.travel.service;

import com.stylefeng.guns.modular.system.model.Images;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 4096 kB 服务类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-23
 */
public interface IImagesService extends IService<Images> {

    List<Map<String, Object>> list(@Param("status") int status);
}
