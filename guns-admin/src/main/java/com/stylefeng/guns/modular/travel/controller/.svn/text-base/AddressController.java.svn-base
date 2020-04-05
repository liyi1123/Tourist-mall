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
import com.stylefeng.guns.modular.system.model.Address;
import com.stylefeng.guns.modular.travel.service.IAddressService;

/**
 * 会员账户控制器
 *
 * @author maolinlong
 * @Date 2018-11-28 17:20:16
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IAddressService addressService;

    /**
     * 跳转到会员账户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "address.html";
    }

    /**
     * 跳转到添加会员账户
     */
    @RequestMapping("/address_add")
    public String addressAdd() {
        return PREFIX + "address_add.html";
    }

    /**
     * 跳转到修改会员账户
     */
    @RequestMapping("/address_update/{addressId}")
    public String addressUpdate(@PathVariable Integer addressId, Model model) {
        Address address = addressService.selectById(addressId);
        model.addAttribute("item",address);
        LogObjectHolder.me().set(address);
        return PREFIX + "address_edit.html";
    }

    /**
     * 获取会员账户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return addressService.selectList(null);
        }else {
            EntityWrapper<Address> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("name",condition);
            return addressService.selectList(entityWrapper);
        }
    }

    /**
     * 新增会员账户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Address address) {
        addressService.insert(address);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员账户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "addressIds[]") Integer[] addressIds) {
        if(null != addressIds && addressIds.length > 0){
                    for(int i=0;i<addressIds.length;i++){
                        addressService.deleteById(addressIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改会员账户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Address address) {
        addressService.updateById(address);
        return SUCCESS_TIP;
    }

    /**
     * 会员账户详情
     */
    @RequestMapping(value = "/detail/{addressId}")
    @ResponseBody
    public Object detail(@PathVariable("addressId") Integer addressId) {
        return addressService.selectById(addressId);
    }
}
