package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.modular.system.model.Images;
import com.stylefeng.guns.modular.system.dao.ImagesMapper;
import com.stylefeng.guns.modular.travel.service.IImagesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 4096 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-23
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements IImagesService {

    @Autowired
    private ImagesMapper imagesMapper;


    @Override
    public List<Map<String, Object>> list(int status) {
        return imagesMapper.list(status);
    }
}
