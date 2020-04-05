package com.stylefeng.guns.modular.travel.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Brand;
import com.stylefeng.guns.modular.travel.service.IBrandService;

/**
 * 酒店品牌控制器
 *
 * @author fengshuonan
 * @Date 2018-07-30 08:52:56
 */
@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IBrandService brandService;

    /**
     * 跳转到酒店品牌首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "brand.html";
    }

    /**
     * 跳转到添加酒店品牌
     */
    @RequestMapping("/brand_add")
    public String brandAdd() {
        return PREFIX + "brand_add.html";
    }

    /**
     * 跳转到修改酒店品牌
     */
    @RequestMapping("/brand_update/{brandId}")
    public String brandUpdate(@PathVariable Integer brandId, Model model) {
        Brand brand = brandService.selectById(brandId);
        model.addAttribute("item",brand);
        LogObjectHolder.me().set(brand);
        return PREFIX + "brand_edit.html";
    }

    /**
     * 获取酒店品牌列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return brandService.selectList(null);
    }

    /**
     * 新增酒店品牌
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Brand brand) {
        brandService.insert(brand);
        return SUCCESS_TIP;
    }

    /**
     * 删除酒店品牌
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "brandIds[]") Integer[] brandIds) {
        if(null != brandIds && brandIds.length > 0){
                    for(int i=0;i<brandIds.length;i++){
                        brandService.deleteById(brandIds[i]);

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改酒店品牌
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Brand brand) {
        brandService.updateById(brand);
        return SUCCESS_TIP;
    }

    /**
     * 酒店品牌详情
     */
    @RequestMapping(value = "/detail/{brandId}")
    @ResponseBody
    public Object detail(@PathVariable("brandId") Integer brandId) {
        return brandService.selectById(brandId);
    }
}
