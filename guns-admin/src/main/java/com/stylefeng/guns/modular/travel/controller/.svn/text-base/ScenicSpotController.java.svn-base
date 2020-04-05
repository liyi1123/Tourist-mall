package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.*;

import com.stylefeng.guns.modular.travel.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * 景点控制器
 *
 * @author fengshuonan
 * @Date 2018-07-17 10:35:40
 */
@Controller
@RequestMapping("/scenicSpot")
public class ScenicSpotController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private  IScenicSpotService scenicSpotService;

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IImagesService imagesService;

    @Autowired
    private IThemeService themeService;

    @Autowired
    private IProductMThemeService productMThemeService;

    /**
     * 跳转到景点首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "scenicSpot.html";
    }

    /**
     * 跳转到添加景点
     */
    @RequestMapping("/scenicSpot_add")
    public String scenicSpotAdd(Model m) {
        Integer userId = ShiroKit.getUser().getId();
        EntityWrapper<Images> imagesEntityWrapper = new EntityWrapper<>();
        imagesEntityWrapper.where("sysUserId = {0}", userId).and("`status` != {0}", -1);
        List<Images> imagesList = imagesService.selectList(imagesEntityWrapper);
        List<ProductType> productTypeList = productTypeService.selectList(new EntityWrapper<ProductType>().where("`desc` = {0}", Common.PRODUCTTYPE_SCIENCE));
        List<Theme> themeList = themeService.selectList(null);


        m.addAttribute("productTypeList", productTypeList)
                .addAttribute("imagesList", imagesList)
                .addAttribute("themeList",themeList);
        return PREFIX + "scenicSpot_add.html";
    }

    /**
     * 跳转到修改景点
     */
    @RequestMapping("/scenicSpot_update/{scenicSpotId}")
    public String scenicSpotUpdate(@PathVariable Integer scenicSpotId, Model model) {

        List<ProductType> productTypeList = productTypeService.selectList(null);
            ScenicSpot scenicSpot = this.scenicSpotService.selectById(scenicSpotId);

        //查出所有主题
        List<Theme> themeList = themeService.selectList(null);

        List<Integer> themeIds = productMThemeService.getThemeIdsByProductIdAndType(scenicSpotId, PRODUCT_M_THEMETHEME_TYPE_1);


        model.addAttribute("productTypeList",productTypeList)
                .addAttribute("item",scenicSpot)
                .addAttribute("themeList",themeList)
                .addAttribute("scenicSpotThemeIds",themeIds);

        model.addAttribute("regionName", ConstantFactory.me().getRegionName(scenicSpot.getRegionId()));
        LogObjectHolder.me().set(scenicSpot);
        return PREFIX + "scenicSpot_edit.html";

    }

    /**
     * 获取景点列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return scenicSpotService.list(condition);
    }

    /**
     * 新增景点
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestParam(value = "themeIds[]")Integer[] themeIds,ScenicSpot scenicSpot) {

        scenicSpotService.addScenicSpot(scenicSpot);
        ProductMTheme productMTheme = null;
        if(ToolUtil.isNotEmpty(themeIds) && themeIds.length > 0){
            for(int i=0;i<themeIds.length;i++){
                productMTheme = new ProductMTheme();
                productMTheme.setProductId(scenicSpot.getId());
                productMTheme.setThemeId(themeIds[i]);
                productMTheme.setType(PRODUCT_M_THEMETHEME_TYPE_1);
                productMThemeService.insert(productMTheme);
            }

        }
        return SUCCESS_TIP;
    }

    /**
     * 删除景点|
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "scenicSpotIds[]") Integer[] scenicSpotIds) {
        if(null != scenicSpotIds && scenicSpotIds.length > 0){
                    for(int i=0;i<scenicSpotIds.length;i++){
                        scenicSpotService.deleteById(scenicSpotIds[i]);

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改景点
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestParam(value = "oldThemeIds[]")Integer[] oldThemeIds,
                         @RequestParam(value = "themeIds[]")Integer[] themeIds,
                         ScenicSpot scenicSpot) {
        /*拿到新的主题IdList      旧的主题IdList,然后做两次循环,
        *第一次循环(增加新的主题ID)
        *第二次循环(删除取消的主题ID)
        */
        Integer productId = scenicSpot.getId();
        scenicSpotService.updateById(scenicSpot);

        //第一次循环
        for(int j=0;j<themeIds.length;j++){
            if(!Arrays.asList(oldThemeIds).contains(themeIds[j])){
                ProductMTheme p = new ProductMTheme();
                p.setProductId(productId);
                p.setType(PRODUCT_M_THEMETHEME_TYPE_1);
                p.setThemeId(themeIds[j]);
                productMThemeService.insert(p);
            }
        }

        //第二次循环,删除多余的themeId
        for(int i=0;i<oldThemeIds.length;i++){
            if(!Arrays.asList(themeIds).contains(oldThemeIds[i])){
                productMThemeService.delete(new EntityWrapper<ProductMTheme>()
                        .where("productId = {0}",scenicSpot.getId())
                        .and("themeId = {0}",oldThemeIds[i])
                        .and("type = {0}", PRODUCT_M_THEMETHEME_TYPE_1)
                );
            }
        }




        return SUCCESS_TIP;
    }

    /**
     * 景点详情
     */
    @RequestMapping(value = "/detail/{scenicSpotId}")
    @ResponseBody
    public Object detail(@PathVariable("scenicSpotId") Integer scenicSpotId) {
        return scenicSpotService.selectById(scenicSpotId);
    }

    /**
     * 跳转到景点首页
     */
    @RequestMapping("/t")
    public String test() {


        return PREFIX + "scenicSpotTest.html";
    }

}
