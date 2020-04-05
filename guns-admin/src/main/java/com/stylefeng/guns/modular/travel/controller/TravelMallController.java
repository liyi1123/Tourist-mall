package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.travel.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/travel")
public class TravelMallController {
    private static Logger log = LoggerFactory.getLogger(TravelMallController.class);
    //地址前缀
    private String PREFIX = "/travel/";

    @Autowired
    private IImagesService iImagesService;

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private IScenicSpotService scenicSpotService;

    @Autowired
    private IVisaNationService visaNationService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRentCarService rentCarService;

    @Autowired
    private IRegionService regionService;


    /**
     * 旅游商场首页
     * @param model
     * @param
     * @return
     */
    @RequestMapping("")
    public String getTravel(Model model){
        List<Map<String,Object>> imgList = iImagesService.list(3);
        List<Map<String,Object>> imgProductList = iImagesService.list(2);
        List<Map<String,Object>> hotelProductList = iImagesService.list(4);
        List<Map<String,Object>> imgCarList = iImagesService.list(5);
        List<ProductType> productTypeList = productTypeService.list(Common.PRODUCTTYPE_SCIENCE);            //景点
        List<ProductType> hotelTypeList = productTypeService.list(Common.PRODUCTTYPE_HOTEL);                //酒店
        List<ProductType> rentCarTypeList = productTypeService.list(Common.PRODUCTTYPE_CAR);                //租车的类型
        List<VisaNation> visaNationList = visaNationService.list(7);                               //签证国家
        List<VisaNation> continentTypeList = visaNationService.list(1);                            //签证州

        /* 获取对应景点类型下最新的前八个景点类型对象栏 Begin*/

        List scenicList = new LinkedList();
        if(ToolUtil.isNotEmpty(productTypeList)){
            for(int i=0;i<productTypeList.size();i++){
                List<ScenicSpot> s = scenicSpotService.listScenic(productTypeList.get(i).getId());
                scenicList.add(s);
            }
        }

        /* 获取对应景点类型下最新的前八个景点类型对象栏 End*/

        /* 获取对应酒店类型下最新的前八个酒店类型对象栏 Begin*/

        List hotelData=new LinkedList();
        if(ToolUtil.isNotEmpty(hotelTypeList)){
            for(int i=0;i<hotelTypeList.size();i++){
                List<Hotel> s = hotelService.listHotel(hotelTypeList.get(i).getId(),null);
                hotelData.add(s);
            }
        }

        /* 获取对应酒店类型下最新的前八个酒店类型对象栏 End*/

        /* 获取对应租车类型下最新的前八个租车类型对象栏 Begin*/

        List carData = new LinkedList();
        if(ToolUtil.isNotEmpty(rentCarTypeList)){
            for(int i=0;i<rentCarTypeList.size();i++){
                List<RentCar> s = rentCarService.rentCar(rentCarTypeList.get(i).getId());
                carData.add(s);
            }
        }

        /* 获取对应租车类型下最新的前八个租车类型对象栏 End*/

        /* 获取对应签证洲下最新的前八个签证国家对象栏 Begin*/

        List visaNationData = new LinkedList();
        if(ToolUtil.isNotEmpty(continentTypeList)){
            for(int i=0;i<continentTypeList.size();i++){
                List<VisaNation> v =visaNationService.list(continentTypeList.get(i).getId());
                visaNationData.add(v);
            }
        }

        /* 获取对应签证洲下最新的前八个签证国家对象栏 End*/



        model.addAttribute("imgList",imgList)
             .addAttribute("imgProductList",imgProductList)
             .addAttribute("hotelTypeList",hotelTypeList)
             .addAttribute("hotelProductList",hotelProductList)
             .addAttribute("imgCarList",imgCarList)
             .addAttribute("rentCarTypeList",rentCarTypeList)
             .addAttribute("continentTypeList",continentTypeList)
             .addAttribute("productTypeList",productTypeList)
             .addAttribute("scenicList",scenicList)
             .addAttribute("visaNationList",visaNationList)
             .addAttribute("hotelData",hotelData)
             .addAttribute("carData",carData)
             .addAttribute("visaNationData",visaNationData);


        return "/travel.html";
    }


    /**
     *根据景点ID查看景点商品详情
     * @param scenicSpotId {景点ID}
     * @param model
     * @return
     */
    @RequestMapping(value = "/getScenicSpotDetail/{scenicSpotId}")
    public String getScenicSpotDetail(@PathVariable Integer scenicSpotId,Model model){
        Map<String,Object> scenicSpot = scenicSpotService.select(scenicSpotId);
        model.addAttribute("scenicSpot",scenicSpot);
        return "/scenicSpotDetail.html";
    }

    /**
     *根据 产品类型ID 查看产品此产品类型List
     * @param productTypeId{产品类型ID}
     * @param model
     * @return
     */
    /*@RequestMapping(value = "/scenicSpotList")
    public String getScenicSpotListDetail(@RequestParam(required = false)  Integer productTypeId, Model model) {
        Page<ScenicSpot> page = new PageFactory<ScenicSpot>().defaultPage();
        // 得到 产品类型
        ProductType productType = null;
        if(ToolUtil.isNotEmpty(productTypeId)){
            productType = productTypeService.select(productTypeId);
            Map<String,Object> condition = new HashMap<>();
            condition.put("productTypeId",productTypeId);
            page.setCondition(condition);
        }

        //产品 区域 列表List
        List<Region> regionList = regionService.selectList(null);

//        List<Map<String, Object>> scenicSpotListDetail = scenicSpotService.scenicTypeList(productTypeId);
        List<Map<String, Object>> scenicSpotList = scenicSpotService.scenicSpotList(page,null);

        List<ProductType> productTypeList = productTypeService.list(Common.PRODUCTTYPE_SCIENCE);
        model.addAttribute("product", ToolUtil.isEmpty(productType) ? "" : productType)
                .addAttribute("regionList", regionList)
                .addAttribute("scenicSpotListDetail", scenicSpotList)
                .addAttribute("productTypeList", productTypeList)
                .addAttribute("page",page);
        log.info(page.toString());
        return PREFIX + "scenicSpotList.html";
    }*/

    /**根据 产品类型ID 查看产品此产品类型List    ResT 风格
     *
     * @param model
     * @param current 当前页
     * @param limit 每页显示条数
     * @param productTypeId 产品类型ID
     * @param regionId 所属区域ID
     * @return
     */
    @RequestMapping(value = "/scenicSpotList/{current}/{limit}/{productTypeId}/{regionId}")
    public String getScenicSpotListDetail(Model model,
                                          @PathVariable(value = "current", required = false) Integer current,
                                          @PathVariable(value = "limit", required = false) Integer limit,
                                          @PathVariable(value = "productTypeId", required = false) Integer productTypeId,
                                          @PathVariable(value = "regionId", required = false) Integer regionId) {
        Page<ScenicSpot> page = new Page<>(current,limit,"a.`id`",false);
        // 得到 产品类型
        ProductType currProductType = null;
        if (ToolUtil.isNotEmpty(productTypeId) && productTypeId != 0) {
            currProductType = productTypeService.select(productTypeId);
        }
        //产品 区域 列表List
        List<Region> regionList = regionService.selectList(null);

                List<ProductType> productTypeList = productTypeService.list(Common.PRODUCTTYPE_SCIENCE);
        List<Map<String, Object>> scenicSpotList = scenicSpotService.scenicSpotList(page,
                (ToolUtil.isEmpty(productTypeId) || 0 == productTypeId) ? null : productTypeId,
                (ToolUtil.isEmpty(regionId) || 0 == regionId) ? null : regionId);
        model.addAttribute("productTypeId", (ToolUtil.isEmpty(productTypeId) || 0 == productTypeId) ? 0 : productTypeId)
                .addAttribute("regionList", regionList)
                .addAttribute("scenicSpotListDetail", scenicSpotList)
                .addAttribute("productTypeList", productTypeList)
                .addAttribute("currRegionId", regionId)
                .addAttribute("currProductType",currProductType)
                .addAttribute("page", page);
        return PREFIX + "scenicSpotList.html";
    }

}
