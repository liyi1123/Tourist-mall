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
import com.stylefeng.guns.modular.system.model.Services;
import com.stylefeng.guns.modular.travel.service.IServicesService;

/**
 * 选配服务控制器
 *
 * @author maolinlong
 * @Date 2018-11-27 17:08:03
 */
@Controller
@RequestMapping("/services")
public class ServicesController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IServicesService servicesService;

    /**
     * 跳转到选配服务首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "services.html";
    }

    /**
     * 跳转到添加选配服务
     */
    @RequestMapping("/services_add")
    public String servicesAdd() {
        return PREFIX + "services_add.html";
    }

    /**
     * 跳转到修改选配服务
     */
    @RequestMapping("/services_update/{servicesId}")
    public String servicesUpdate(@PathVariable Integer servicesId, Model model) {
        Services services = servicesService.selectById(servicesId);
        model.addAttribute("item",services);
        LogObjectHolder.me().set(services);
        return PREFIX + "services_edit.html";
    }

    /**
     * 获取选配服务列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return servicesService.selectList(null);
        }else {
            EntityWrapper<Services> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("name",condition);
            return servicesService.selectList(entityWrapper);
        }
    }

    /**
     * 新增选配服务
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Services services) {
        servicesService.insert(services);
        return SUCCESS_TIP;
    }

    /**
     * 删除选配服务
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "servicesIds[]") Integer[] servicesIds) {
        if(null != servicesIds && servicesIds.length > 0){
                    for(int i=0;i<servicesIds.length;i++){
                        servicesService.deleteById(servicesIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改选配服务
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Services services) {
        servicesService.updateById(services);
        return SUCCESS_TIP;
    }

    /**
     * 选配服务详情
     */
    @RequestMapping(value = "/detail/{servicesId}")
    @ResponseBody
    public Object detail(@PathVariable("servicesId") Integer servicesId) {
        return servicesService.selectById(servicesId);
    }
}
