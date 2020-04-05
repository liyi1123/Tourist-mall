package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.ProductType;
import com.stylefeng.guns.modular.travel.service.IProductTypeService;

/**
 * 产品类型控制器
 *
 * @author fengshuonan
 * @Date 2018-07-16 09:42:44
 */
@Controller
@RequestMapping("/productType")
public class ProductTypeController extends BaseController {

    /* private String PREFIX = "/travel/productType/"; */
    private String PREFIX = "/travel/";

    @Autowired
    private IProductTypeService productTypeService;

    /**
     * 跳转到产品类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "productType.html";
    }

    /**
     * 跳转到添加产品类型
     */
    @RequestMapping("/productType_add")
    public String productTypeAdd() {
        return PREFIX + "productType_add.html";
    }

    /**
     * 跳转到修改产品类型
     */
    @RequestMapping("/productType_update/{productTypeId}")
    public String productTypeUpdate(@PathVariable Integer productTypeId, Model model) {
        ProductType productType = productTypeService.selectById(productTypeId);
        model.addAttribute("item",productType);
        LogObjectHolder.me().set(productType);
        return PREFIX + "productType_edit.html";
    }

    /**
     * 获取产品类型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //判断condition是否有值
        if (ToolUtil.isEmpty(condition)) {
            //如果没有值,则表示查询全部
            return productTypeService.selectList(null);
        } else {
            //如果有值则是按照业务名称进行模糊查询
            EntityWrapper<ProductType> entityWrapper=new EntityWrapper<>();
            Wrapper<ProductType> wrapper=entityWrapper.like("name",condition);
            return productTypeService.selectList(wrapper);
        }
    }

    /**
     * 新增产品类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ProductType productType) {
        productTypeService.insert(productType);
        return SUCCESS_TIP;
    }

    /**
     * 删除产品类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "productTypeIds[]") Integer[] productTypeIds) {
        if(null != productTypeIds && productTypeIds.length > 0){
                    for(int i=0;i<productTypeIds.length;i++){
                        productTypeService.deleteById(productTypeIds[i]);

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改产品类型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ProductType productType) {
        productTypeService.updateById(productType);
        return SUCCESS_TIP;
    }

    /**
     * 产品类型详情
     */
    @RequestMapping(value = "/detail/{productTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("productTypeId") Integer productTypeId) {
        return productTypeService.selectById(productTypeId);
    }
}
