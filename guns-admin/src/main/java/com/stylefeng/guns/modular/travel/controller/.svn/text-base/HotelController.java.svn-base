package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.travel.service.IBrandService;
import com.stylefeng.guns.modular.travel.service.IImagesService;
import com.stylefeng.guns.modular.travel.service.IProductTypeService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.travel.service.IHotelService;

import java.util.List;

/**
 * 酒店业务控制器
 *
 * @author fengshuonan
 * @Date 2018-07-30 09:17:36 1122
 */
@Controller
@RequestMapping("/hotel")
public class HotelController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private IImagesService imagesService;

    /**
     * 跳转到酒店业务首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotel.html";
    }

    /**
     * 跳转到添加酒店业务
     */
    @RequestMapping("/hotel_add")
    public String hotelAdd(Model m) {

        Integer userId = ShiroKit.getUser().getId();
        EntityWrapper<Images> imagesEntityWrapper = new EntityWrapper<>();
        imagesEntityWrapper.where("sysUserId = {0}", userId).and("`status` != {0}", -1);
        List<Images> imagesList = imagesService.selectList(imagesEntityWrapper);

        List<ProductType> productTypeList = productTypeService.selectList(new EntityWrapper<ProductType>().where("`desc`={0}", PRODUCT_TYPE_DESC_1));
        List<Brand> brandList = brandService.selectList(null);

        m.addAttribute("productTypeList", productTypeList)
                .addAttribute("brandList", brandList)
                .addAttribute("imagesList", imagesList);

        return PREFIX + "hotel_add.html";
    }

    /**
     * 跳转到修改酒店业务
     */
    @RequestMapping("/hotel_update/{hotelId}")
    public String hotelUpdate(@PathVariable Integer hotelId, Model model) {

        Integer userId =ShiroKit.getUser().getId();
        List<Images> imagesList = imagesService.selectList(new EntityWrapper<Images>().where("sysUserId = {0}",userId).and("`status` !={0}",-1));

        List<ProductType> productTypeList = productTypeService.selectList(new EntityWrapper<ProductType>().where("`desc`={0}", PRODUCT_TYPE_DESC_1));

        List<Brand> brandList = brandService.selectList(null);

         /* = productTypeService.list();*/
        Hotel hotel = hotelService.selectById(hotelId);
        model.addAttribute("item",hotel)
        .addAttribute("productTypeList",productTypeList)
        .addAttribute("imagesList",imagesList)
        .addAttribute("brandList",brandList)
        .addAttribute("regionName", ConstantFactory.me().getRegionName(hotel.getRegionId()));
        LogObjectHolder.me().set(hotel);
        return PREFIX + "hotel_edit.html";
    }

    /**
     * 获取酒店业务列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelService.list(condition);
    }

    /**
     * 新增酒店业务
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Hotel hotel) {
        hotelService.insert(hotel);
        return SUCCESS_TIP;
    }

    /**
     * 删除酒店业务
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "hotelIds[]") Integer[] hotelIds) {
        if(null != hotelIds && hotelIds.length > 0){
                    for(int i=0;i<hotelIds.length;i++){
                        hotelService.deleteById(hotelIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改酒店业务
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Hotel hotel) {
        hotelService.updateById(hotel);
        return SUCCESS_TIP;
    }

    /**
     * 酒店业务详情
     */
    @RequestMapping(value = "/detail/{hotelId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelId") Integer hotelId) {
        return hotelService.selectById(hotelId);
    }
}
