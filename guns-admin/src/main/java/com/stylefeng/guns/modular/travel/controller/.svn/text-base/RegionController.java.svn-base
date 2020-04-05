package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Dept;
import com.stylefeng.guns.modular.system.model.Region;
import com.stylefeng.guns.modular.travel.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 所属区域控制器
 *
 * @author fengshuonan
 * @Date 2018-07-16 10:14:52
 */
@Controller
@RequestMapping("/region")
public class RegionController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IRegionService regionService;

    /**
     * 跳转到所属区域首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "region.html";
    }

    /**
     * 跳转到添加所属区域
     */
    @RequestMapping("/region_add")
    public String regionAdd() {
        return PREFIX + "region_add.html";
    }

    /**
     * 跳转到修改所属区域
     */
    @RequestMapping("/region_update/{regionId}")
    public String regionUpdate(@PathVariable Integer regionId, Model model) {
        Region region = regionService.selectById(regionId);
        model.addAttribute("item",region);
        LogObjectHolder.me().set(region);
        return PREFIX + "region_edit.html";
    }

    /**
     * 获取所属区域列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //判断condition是否有值
        if (ToolUtil.isEmpty(condition)) {
            //如果没有值,则表示查询全部
            return regionService.selectList(null);
        } else {
            //如果有值则是按照业务名称进行模糊查询
            EntityWrapper<Region> entityWrapper=new EntityWrapper<>();
            Wrapper<Region> wrapper=entityWrapper.like("name",condition);
            return regionService.selectList(wrapper);
        }

    }

    /**
     * 新增所属区域
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Region region) {
        if (ToolUtil.isOneEmpty(region, region.getName())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //设置'所属区域'的父ID List
        regionSetPids(region);
        regionService.insert(region);
        return SUCCESS_TIP;
    }

    /**
     * 删除所属区域
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "regionIds[]") Integer[] regionIds) {
        if(null != regionIds && regionIds.length > 0){
                    for(int i=0;i<regionIds.length;i++){
                        regionService.deleteById(regionIds[i]);

                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改所属区域
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Region region) {
        regionService.updateById(region);
        return SUCCESS_TIP;
    }

    /**
     * 所属区域详情
     */
    @RequestMapping(value = "/detail/{regionId}")
    @ResponseBody
    public Object detail(@PathVariable("regionId") Integer regionId) {
        return regionService.selectById(regionId);
    }

    /**
     * 获取所属区域的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = regionService.tree();
        return tree;
    }



    private void regionSetPids(Region region){

        if(ToolUtil.isEmpty(region.getParentId()) || region.getParentId().equals(0)){
            region.setParentId(0);
            region.setParentIdList("[0],");
        }else{
            Integer pid=region.getParentId();
            Region temp=regionService.selectRegionById(pid);
            String pids=temp.getParentIdList();
            region.setParentId(pid);
            region.setParentIdList(pids+"["+pid+"],");
        }
    }


}
