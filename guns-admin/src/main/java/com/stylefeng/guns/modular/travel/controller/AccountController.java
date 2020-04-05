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
import com.stylefeng.guns.modular.system.model.Account;
import com.stylefeng.guns.modular.travel.service.IAccountService;

/**
 * 会员账户控制器
 *
 * @author maolinlong
 * @Date 2018-11-28 17:15:47
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private IAccountService accountService;

    /**
     * 跳转到会员账户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "account.html";
    }

    /**
     * 跳转到添加会员账户
     */
    @RequestMapping("/account_add")
    public String accountAdd() {
        return PREFIX + "account_add.html";
    }

    /**
     * 跳转到修改会员账户
     */
    @RequestMapping("/account_update/{accountId}")
    public String accountUpdate(@PathVariable Integer accountId, Model model) {
        Account account = accountService.selectById(accountId);
        model.addAttribute("item",account);
        LogObjectHolder.me().set(account);
        return PREFIX + "account_edit.html";
    }

    /**
     * 获取会员账户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return accountService.selectList(null);
        }else {
            EntityWrapper<Account> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("name",condition);
            return accountService.selectList(entityWrapper);
        }
    }

    /**
     * 新增会员账户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Account account) {
        accountService.insert(account);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员账户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "accountIds[]") Integer[] accountIds) {
        if(null != accountIds && accountIds.length > 0){
                    for(int i=0;i<accountIds.length;i++){
                        accountService.deleteById(accountIds[i]);
                    }
                }
        return SUCCESS_TIP;
    }

    /**
     * 修改会员账户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Account account) {
        accountService.updateById(account);
        return SUCCESS_TIP;
    }

    /**
     * 会员账户详情
     */
    @RequestMapping(value = "/detail/{accountId}")
    @ResponseBody
    public Object detail(@PathVariable("accountId") Integer accountId) {
        return accountService.selectById(accountId);
    }
}
