package com.stylefeng.guns.modular.travel.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.model.ScenicSpot;
import com.stylefeng.guns.modular.system.dao.ScenicSpotMapper;
import com.stylefeng.guns.modular.travel.service.IScenicSpotService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-17
 */
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements IScenicSpotService {
    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Override
    public List<Map<String, Object>> list(String condition) {
        return scenicSpotMapper.list(condition);
    }


    /**
     * @Author: maoLinLong
     * @Date: 2018/7/30
     * @Description:返回新增景点对象的ID
     * @Param: [scenicSpot]
     * @return: int scenicSpotID
     */
    @Override
    public int addScenicSpot(ScenicSpot scenicSpot) {

        return scenicSpotMapper.addScenicSpot(scenicSpot);
    }

    @Override
    public List<ScenicSpot> listScenic(Integer productTypeId) {
        return scenicSpotMapper.listScenic(productTypeId);
    }

    @Override
    public Map<String, Object> select(Integer scenicSpotId) {
        return scenicSpotMapper.select(scenicSpotId);
    }

    @Override
    public List<Map<String, Object>> scenicTypeList(Integer productTypeId) {
        return scenicSpotMapper.scenicTypeList(productTypeId);
    }


    /**mll
     * 景点信息List分页
     * @param page
     * @param productTypeId
     * @return
     */
    @Override
    public List<Map<String, Object>> scenicSpotList(Page<ScenicSpot> page, Integer productTypeId,Integer regionId) {
        return scenicSpotMapper.scenicSpotList(page,productTypeId,regionId);
    }

    /**
     * 景点信息总条数
     * @param page
     * @param productTypeId
     * @return {Integer}
     */
    /*@Override
    public Integer scenicSpotListCount(Page<ScenicSpot> page, Integer productTypeId) {
        return scenicSpotMapper.scenicSpotListCount(page,productTypeId);
    }*/


}
