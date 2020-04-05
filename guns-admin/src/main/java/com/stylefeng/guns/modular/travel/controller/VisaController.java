package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.VisaType;
import com.stylefeng.guns.modular.travel.service.IVisaTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Visa;
import com.stylefeng.guns.modular.travel.service.IVisaService;

import java.util.List;

/**
 * 签证管理控制器
 *
 * @author maolinlong
 * @Date 2018-08-06 14:54:34
 */
@Controller
@RequestMapping("/visa")
public class VisaController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IVisaService visaService;

    @Autowired
    private IVisaTypeService visaTypeService;

    /**
     * 跳转到签证管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "visa.html";
    }

    /**
     * 跳转到添加签证管理
     */
    @RequestMapping("/visa_add")
    public String visaAdd(Model model) {

        List<VisaType> visaTypeList=visaTypeService.selectList(null);
        model.addAttribute("visaTypeList",visaTypeList);
        return PREFIX + "visa_add.html";
    }

    /**
     * 跳转到修改签证管理
     */
    @RequestMapping("/visa_update/{visaId}")
    public String visaUpdate(@PathVariable Integer visaId, Model model) {


        List<VisaType> visaTypeList=visaTypeService.selectList(null);

        Visa visa = visaService.selectById(visaId);
        model.addAttribute("item",visa)
                .addAttribute("visaTypeList",visaTypeList)
                .addAttribute("visaNationName", ConstantFactory.me().getVisaNationName(visa.getVisaNationId()))
                .addAttribute("regionName",ConstantFactory.me().getRegionName(visa.getIssueAt()));

        LogObjectHolder.me().set(visa);
        return PREFIX + "visa_edit.html";
    }

    /**
     * 获取签证管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return visaService.list(null);
        }else {
            EntityWrapper<Visa> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("name",condition);
            return visaService.list(condition);
        }
    }

    /**
     * 新增签证管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Visa visa) {
        Integer userId = ShiroKit.getUser().getId();
        visa.setSysUserId(userId);
        visaService.insert(visa);
        return SUCCESS_TIP;
    }

    /**
     * 删除签证管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "visaIds[]") Integer[] visaIds) {
        if(null != visaIds && visaIds.length > 0){
                    for(int i=0;i<visaIds.length;i++){
                        visaService.deleteById(visaIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改签证管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Visa visa) {
        visaService.updateById(visa);
        return SUCCESS_TIP;
    }

    /**
     * 签证管理详情
     */
    @RequestMapping(value = "/detail/{visaId}")
    @ResponseBody
    public Object detail(@PathVariable("visaId") Integer visaId) {
        return visaService.selectById(visaId);
    }
}
