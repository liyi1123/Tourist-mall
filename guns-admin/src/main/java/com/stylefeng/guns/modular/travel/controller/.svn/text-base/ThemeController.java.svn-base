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
import com.stylefeng.guns.modular.system.model.Theme;
import com.stylefeng.guns.modular.travel.service.IThemeService;

/**
 * 热门主题控制器
 *
 * @author fengshuonan
 * @Date 2018-07-16 14:57:15
 */
@Controller
@RequestMapping("/theme")
public class ThemeController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IThemeService themeService;

    /**
     * 跳转到热门主题首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "theme.html";
    }

    /**
     * 跳转到添加热门主题
     */
    @RequestMapping("/theme_add")
    public String themeAdd() {
        return PREFIX + "theme_add.html";
    }

    /**
     * 跳转到修改热门主题
     */
    @RequestMapping("/theme_update/{themeId}")
    public String themeUpdate(@PathVariable Integer themeId, Model model) {
        Theme theme = themeService.selectById(themeId);
        model.addAttribute("item", theme);
        LogObjectHolder.me().set(theme);
        return PREFIX + "theme_edit.html";
    }

    /**
     * 获取热门主题列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //判断condition是否有值
        if (ToolUtil.isEmpty(condition)) {
            //如果没有值,则表示查询全部
            return themeService.selectList(null);
        } else {
            //如果有值则是按照业务名称进行模糊查询
            EntityWrapper<Theme> entityWrapper=new EntityWrapper<>();
            Wrapper<Theme> wrapper=entityWrapper.like("name",condition);
            return themeService.selectList(wrapper);
        }
    }


    /**
     * 新增热门主题
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Theme theme) {
        themeService.insert(theme);
        return SUCCESS_TIP;
    }

    /**
     * 删除热门主题
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "themeIds[]") Integer[] themeIds) {
        if(null != themeIds && themeIds.length > 0){
                    for(int i=0;i<themeIds.length;i++){
                        themeService.deleteById(themeIds[i]);

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改热门主题
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Theme theme) {
        themeService.updateById(theme);
        return SUCCESS_TIP;
    }

    /**
     * 热门主题详情
     */
    @RequestMapping(value = "/detail/{themeId}")
    @ResponseBody
    public Object detail(@PathVariable("themeId") Integer themeId) {
        return themeService.selectById(themeId);
    }
}
