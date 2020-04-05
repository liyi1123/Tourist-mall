package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.CarBrand;
import com.stylefeng.guns.modular.system.model.ProductType;
import com.stylefeng.guns.modular.system.model.RentCar;
import com.stylefeng.guns.modular.travel.service.ICarBrandService;
import com.stylefeng.guns.modular.travel.service.IProductTypeService;
import com.stylefeng.guns.modular.travel.service.IRentCarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/travelRent")
public class TravelRentController {
    private  static Logger log = Logger.getLogger(TravelRentController.class);

    private String PREFIX = "/travel/";

    @Autowired
    private IRentCarService rentCarService;

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private ICarBrandService carBrandService;

    @RequestMapping("/rentCarList/{current}/{limit}/{productTypeId}/{carBrandId}/{seatCount}")
    public String getTravelRent(Model model,
                                @PathVariable(value = "current",required = false) Integer current,
                                @PathVariable(value = "limit",required = false) Integer limit,
                                @PathVariable(value = "productTypeId",required = false) Integer productTypeId,
                                @PathVariable(value = "carBrandId",required = false) Integer carBrandId,
                                @PathVariable(value = "seatCount",required = false) Integer seatCount){
        Page<RentCar> page = new PageFactory<RentCar>().defaultPage();
        page.setCurrent(current).setSize(limit);

        //得到产品类型
        ProductType currProductType = null;
        if(ToolUtil.isNotEmpty(productTypeId) && productTypeId !=0){
            currProductType = productTypeService.select(productTypeId);
        }

        List<ProductType> productTypeList = productTypeService.list(Common.PRODUCTTYPE_CAR);

        //获得汽车品牌
        EntityWrapper<CarBrand> carBrandEntityWrapper = new EntityWrapper<>();
        carBrandEntityWrapper.where("parentId={0}",0);
        List<CarBrand> carBrandList = carBrandService.selectList(carBrandEntityWrapper);

        List<Map<String,Object>> rentCarList = rentCarService.rentCarList(page,
                (ToolUtil.isEmpty(productTypeId) || 0 == productTypeId) ? null : productTypeId,
                (ToolUtil.isEmpty(carBrandId) || 0 == carBrandId) ? null : carBrandId,
                (ToolUtil.isEmpty(seatCount) || 0 == seatCount) ? null : seatCount);

        model.addAttribute("currProductType",currProductType)
             .addAttribute("productTypeList",productTypeList)
             .addAttribute("carBrandList",carBrandList)
             .addAttribute("rentCarList",rentCarList);
        return PREFIX+"rentCarList.html";
    }

    @RequestMapping("/travelRentDetail/{rentCarId}")
    public String getTravelRentDetail(Model model ){

        return "travelRentDetail.html";
    }



}
