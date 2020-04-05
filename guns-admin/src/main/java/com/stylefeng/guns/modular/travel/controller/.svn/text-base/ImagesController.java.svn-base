package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Images;
import com.stylefeng.guns.modular.travel.service.IImagesService;

import java.io.File;
import java.util.List;

/**
 * 图片管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-23 16:44:00
 */
@Controller
@RequestMapping("/images")
public class ImagesController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IImagesService imagesService;

    /**
     * 跳转到图片管理首页
     */
    @RequestMapping("")
    public String index() {

        return PREFIX + "images.html";
    }

    /**
     * 跳转到添加图片管理
     */
    @RequestMapping("/images_add")
    public String imagesAdd() {
        return PREFIX + "images_add.html";
    }

    /**
     * 跳转到修改图片管理
     */
    @RequestMapping("/images_update/{imagesId}")
    public String imagesUpdate(@PathVariable Integer imagesId, Model model) {
        Images images = imagesService.selectById(imagesId);
        model.addAttribute("item",images);
        LogObjectHolder.me().set(images);
        return PREFIX + "images_edit.html";
    }

    /**
     * 获取图片管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return imagesService.selectList(null);
    }

    /**
     * 新增图片管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Images images) {
        imagesService.insert(images);
        return SUCCESS_TIP;
    }

    /**
     * 删除图片管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "imagesIds[]") Integer[] imagesIds) {
        if(null != imagesIds && imagesIds.length > 0){
                    for(int i=0;i<imagesIds.length;i++){
                        Images image = imagesService.selectById(imagesIds[i]);
                        File img = new File(image.getPrefix() + image.getName());
                        imagesService.deleteById(imagesIds[i]);
                        if(img.exists()){
                            img.delete();
                        }

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改图片管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Images images) {
        imagesService.updateById(images);
        return SUCCESS_TIP;
    }

    /**
     * 图片管理详情
     */
    @RequestMapping(value = "/detail/{imagesId}")
    @ResponseBody
    public Object detail(@PathVariable("imagesId") Integer imagesId) {
        return imagesService.selectById(imagesId);
    }
}
