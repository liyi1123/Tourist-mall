package com.stylefeng.guns.modular.travel.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.node.ZTreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.CarBrand;
import com.stylefeng.guns.modular.travel.service.ICarBrandService;

import java.util.List;

/**
 * 租车服务控制器
 *
 * @author maolinlong
 * @Date 2018-08-01 17:10:25
 */
@Controller
@RequestMapping("/carBrand")
public class CarBrandController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private ICarBrandService carBrandService;

    /**
     * 跳转到租车服务首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "carBrand.html";
    }

    /**
     * 跳转到添加租车服务
     */
    @RequestMapping("/carBrand_add")
    public String carBrandAdd() {
        return PREFIX + "carBrand_add.html";
    }

    /**
     * 跳转到修改租车服务
     */
    @RequestMapping("/carBrand_update/{carBrandId}")
    public String carBrandUpdate(@PathVariable Integer carBrandId, Model model) {
        CarBrand carBrand = carBrandService.selectById(carBrandId);
        model.addAttribute("item",carBrand)
                .addAttribute("carBrandName", ConstantFactory.me().getCarBrandName(carBrand.getParentId()));
        LogObjectHolder.me().set(carBrand);
        return PREFIX + "carBrand_edit.html";
    }

    /**
     * 获取租车服务列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return carBrandService.selectList(null);
    }

    /**
     * 新增租车服务
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CarBrand carBrand) {
        carBrandService.insert(carBrand);
        return SUCCESS_TIP;
    }

    /**
     * 删除租车服务
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "carBrandIds[]") Integer[] carBrandIds) {
        if(null != carBrandIds && carBrandIds.length > 0){
                    for(int i=0;i<carBrandIds.length;i++){
                        carBrandService.deleteById(carBrandIds[i]);

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改租车服务
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CarBrand carBrand) {
        carBrandService.updateById(carBrand);
        return SUCCESS_TIP;
    }

    /**
     * 租车服务详情
     */
    @RequestMapping(value = "/detail/{carBrandId}")
    @ResponseBody
    public Object detail(@PathVariable("carBrandId") Integer carBrandId) {
        return carBrandService.selectById(carBrandId);
    }



    /**
     * 获取车辆品牌的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = carBrandService.tree();
        return tree;
    }

}
