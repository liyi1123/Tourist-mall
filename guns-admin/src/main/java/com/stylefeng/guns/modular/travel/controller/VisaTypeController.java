package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.VisaType;
import com.stylefeng.guns.modular.travel.service.IVisaTypeService;

import java.util.List;

/**
 * 护照类型控制器
 *
 * @author maolinlong
 * @Date 2018-08-06 14:21:49
 */
@Controller
@RequestMapping("/visaType")
public class VisaTypeController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IVisaTypeService visaTypeService;

    /**
     * 跳转到护照类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "visaType.html";
    }

    /**
     * 跳转到添加护照类型
     */
    @RequestMapping("/visaType_add")
    public String visaTypeAdd() {
        return PREFIX + "visaType_add.html";
    }

    /**
     * 跳转到修改护照类型
     */
    @RequestMapping("/visaType_update/{visaTypeId}")
    public String visaTypeUpdate(@PathVariable Integer visaTypeId, Model model) {
        VisaType visaType = visaTypeService.selectById(visaTypeId);
        model.addAttribute("item",visaType);
        LogObjectHolder.me().set(visaType);
        return PREFIX + "visaType_edit.html";
    }

    /**
     * 获取护照类型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return visaTypeService.list(null);
        }else {
            EntityWrapper<VisaType> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("name",condition);
            return visaTypeService.selectList(entityWrapper);
        }
    }

    /**
     * 新增护照类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VisaType visaType) {
        visaTypeService.insert(visaType);
        return SUCCESS_TIP;
    }

    /**
     * 删除护照类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "visaTypeIds[]") Integer[] visaTypeIds) {
        if(null != visaTypeIds && visaTypeIds.length > 0){
                    for(int i=0;i<visaTypeIds.length;i++){
                        visaTypeService.deleteById(visaTypeIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改护照类型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VisaType visaType) {
        visaTypeService.updateById(visaType);
        return SUCCESS_TIP;
    }

    /**
     * 护照类型详情
     */
    @RequestMapping(value = "/detail/{visaTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("visaTypeId") Integer visaTypeId) {
        return visaTypeService.selectById(visaTypeId);
    }


}
