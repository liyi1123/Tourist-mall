package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
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
import com.stylefeng.guns.modular.system.model.VisaNation;
import com.stylefeng.guns.modular.travel.service.IVisaNationService;

import java.util.List;

/**
 * 签证国家控制器
 *
 * @author maolinlong
 * @Date 2018-08-06 14:38:03
 */
@Controller
@RequestMapping("/visaNation")
public class VisaNationController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IVisaNationService visaNationService;

    /**
     * 跳转到签证国家首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "visaNation.html";
    }

    /**
     * 跳转到添加签证国家
     */
    @RequestMapping("/visaNation_add")
    public String visaNationAdd() {
        return PREFIX + "visaNation_add.html";
    }

    /**
     * 跳转到修改签证国家
     */
    @RequestMapping("/visaNation_update/{visaNationId}")
    public String visaNationUpdate(@PathVariable Integer visaNationId, Model model) {
        VisaNation visaNation = visaNationService.selectById(visaNationId);
        model.addAttribute("item",visaNation)
                .addAttribute("visaNationName", ConstantFactory.me().getVisaNationName(visaNation.getParentId()));
        LogObjectHolder.me().set(visaNation);
        return PREFIX + "visaNation_edit.html";
    }

    /**
     * 获取签证国家列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return visaNationService.selectList(null);
        }else {
            EntityWrapper<VisaNation> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("name",condition);
            return visaNationService.selectList(entityWrapper);
        }
    }

    /**
     * 新增签证国家
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VisaNation visaNation) {
        visaNationService.insert(visaNation);
        return SUCCESS_TIP;
    }

    /**
     * 删除签证国家
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "visaNationIds[]") Integer[] visaNationIds) {
        if(null != visaNationIds && visaNationIds.length > 0){
                    for(int i=0;i<visaNationIds.length;i++){
                        visaNationService.deleteById(visaNationIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改签证国家
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VisaNation visaNation) {
        visaNationService.updateById(visaNation);
        return SUCCESS_TIP;
    }

    /**
     * 签证国家详情
     */
    @RequestMapping(value = "/detail/{visaNationId}")
    @ResponseBody
    public Object detail(@PathVariable("visaNationId") Integer visaNationId) {
        return visaNationService.selectById(visaNationId);
    }


    /**
     * 获取签证国家列表
     */
    @RequestMapping(value="/tree")
    @ResponseBody
    public List<ZTreeNode> tree(){
        List<ZTreeNode> tree=visaNationService.tree();
        return tree;
    }
}
