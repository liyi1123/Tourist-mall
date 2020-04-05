package com.stylefeng.guns.modular.travel.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Hotel;
import com.stylefeng.guns.modular.system.dao.HotelMapper;
import com.stylefeng.guns.modular.travel.service.IHotelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-30
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public List<Map<String,Object>> list(String condition){
        return hotelMapper.list(condition);
    }

    @Override
    public List<Hotel> listHotel(Integer productTypeId,Integer hotelId) {
        return hotelMapper.listHotel(productTypeId,hotelId);
    }

    @Override
    public List<Map<String, Object>> hotelList(Page<Hotel> page, Integer productTypeId, Integer gradeId, Integer regionId, Integer brandId) {
        return hotelMapper.hotelList(page,productTypeId,gradeId,regionId,brandId);

    }

    @Override
    public Map<String, Object> hotelDetail(Integer hotelId) {
        return hotelMapper.hotelDetail(hotelId);
    }

}
