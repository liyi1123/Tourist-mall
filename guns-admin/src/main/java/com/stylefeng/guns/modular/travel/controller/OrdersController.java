package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import com.stylefeng.guns.modular.system.model.Orders;
import com.stylefeng.guns.modular.travel.service.IOrdersService;

/**
 * 订单控制器
 *
 * @author maolinlong
 * @Date 2018-11-27 16:58:54
 */
@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IOrdersService ordersService;

    /**
     * 跳转到订单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "orders.html";
    }

    /**
     * 跳转到添加订单
     */
    @RequestMapping("/orders_add")
    public String ordersAdd() {
        return PREFIX + "orders_add.html";
    }

    /**
     * 跳转到修改订单
     */
    @RequestMapping("/orders_update/{ordersId}")
    public String ordersUpdate(@PathVariable Integer ordersId, Model model) {
        Orders orders = ordersService.selectById(ordersId);
        model.addAttribute("item",orders);
        LogObjectHolder.me().set(orders);
        return PREFIX + "orders_edit.html";
    }

    /**
     * 获取订单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return ordersService.selectList(null);
        }else {
            EntityWrapper<Orders> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("name",condition);
            return ordersService.selectList(entityWrapper);
        }
    }

    /**
     * 新增订单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Orders orders) {
        ordersService.insert(orders);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "ordersIds[]") Integer[] ordersIds) {
        if(null != ordersIds && ordersIds.length > 0){
                    for(int i=0;i<ordersIds.length;i++){
                        ordersService.deleteById(ordersIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改订单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Orders orders) {
        ordersService.updateById(orders);
        return SUCCESS_TIP;
    }

    /**
     * 订单详情
     */
    @RequestMapping(value = "/detail/{ordersId}")
    @ResponseBody
    public Object detail(@PathVariable("ordersId") Integer ordersId) {
        return ordersService.selectById(ordersId);
    }
}
