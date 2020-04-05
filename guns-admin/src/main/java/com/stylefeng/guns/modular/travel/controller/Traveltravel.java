package com.stylefeng.guns.modular.travel.controller;

import com.stylefeng.guns.modular.travel.service.IBrandService;
import com.stylefeng.guns.modular.travel.service.IHotelService;
import com.stylefeng.guns.modular.travel.service.IProductTypeService;
import com.stylefeng.guns.modular.travel.service.IRegionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/traveltravel")
public class Traveltravel {
    private static Logger log = Logger.getLogger(Traveltravel.class);

    private String PREFIX = "/travel";

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private IHotelService iHotelService;

    /**
     *
     */
    @RequestMapping("/hotelList/{current}/{limit}/{productTypeId}/{gradeId}/{brandId}")
    public String getTravelHotel(Model model, @PathVariable(value ="current",required = false) Integer currnt,
                                 @PathVariable(value = "limit",required = false) Integer limit,
                                 @PathVariable(value = "productTypeId",required = false) Integer productTypeId,
                                 @PathVariable(value = "grade",required = false) Integer grade,
                                 @PathVariable(value = "brandId",required = false) Integer brandId){
        return null;

    }
}
