package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.modular.system.model.ProductType;
import com.stylefeng.guns.modular.travel.service.IProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.RentCar;
import com.stylefeng.guns.modular.travel.service.IRentCarService;

import java.util.List;

/**
 * 租车管理控制器
 *
 * @author maolinlong
 * @Date 2018-08-02 11:40:49
 */
@Controller
@RequestMapping("/rentCar")
public class RentCarController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IRentCarService rentCarService;

    @Autowired
    private IProductTypeService productTypeService;

    /**
     * 跳转到租车管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "rentCar.html";
    }

    /**
     * 跳转到添加租车管理
     */
    @RequestMapping("/rentCar_add")
    public String rentCarAdd(Model model) {

        Wrapper<ProductType> productTypeEntityWrapper = new EntityWrapper<ProductType>();
        productTypeEntityWrapper.where("`desc`={0}", PRODUCT_TYPE_DESC_3);
//        List<ProductType> productTypeList=productTypeService.selectList(productTypeEntityWrapper); //带有条件限制的查询语句
        List<ProductType> productTypeList = productTypeService.selectList(productTypeEntityWrapper);
//        List<ProductType> productTypeList = productTypeService.selectList(new EntityWrapper<ProductType>().where("`desc`={0}", PRODUCT_TYPE_DESC_3));

        model.addAttribute("productTypeList", productTypeList);

        return PREFIX + "rentCar_add.html";
    }

    /**|
     * 跳转到修改租车管理
     */
    @RequestMapping("/rentCar_update/{rentCarId}")
    public String rentCarUpdate(@PathVariable Integer rentCarId, Model model) {
        RentCar rentCar = rentCarService.selectById(rentCarId);
        List<ProductType> productTypeList = productTypeService.selectList(new EntityWrapper<ProductType>().where("`desc`={0}", PRODUCT_TYPE_DESC_3));
        System.out.println(rentCar.toString());
        model.addAttribute("item",rentCar)
        .addAttribute("productTypeList",productTypeList)
        .addAttribute("carBrandName", ConstantFactory.me().getCarBrandName(rentCar.getCarBrandId()));
        LogObjectHolder.me().set(rentCar);
        return PREFIX + "rentCar_edit.html";
    }

    /**
     * 获取租车管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return rentCarService.list(condition);
    }

    /**
     * 新增租车管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(RentCar rentCar) {
        rentCarService.insert(rentCar);
        return SUCCESS_TIP;
    }

    /**
     * 删除租车管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "rentCarIds[]") Integer[] rentCarIds) {
        if(null != rentCarIds && rentCarIds.length > 0){
                    for(int i=0;i<rentCarIds.length;i++){
                        rentCarService.deleteById(rentCarIds[i]);

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改租车管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(RentCar rentCar) {
        rentCarService.updateById(rentCar);
        return SUCCESS_TIP;
    }

    /**
     * 租车管理详情
     */
    @RequestMapping(value = "/detail/{rentCarId}")
    @ResponseBody
    public Object detail(@PathVariable("rentCarId") Integer rentCarId) {
        return rentCarService.selectById(rentCarId);
    }
}
