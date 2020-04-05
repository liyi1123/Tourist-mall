package com.stylefeng.guns.core.common.constant.factory;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.state.Order;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.ToolUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        //获得request
        HttpServletRequest request = HttpKit.getRequest();
        String limit2 = request.getParameter("limit");
        int limit = Integer.valueOf(ToolUtil.isEmpty(limit2) ? "10" : limit2);     //每页多少条数据

        String offset2 = request.getParameter("offset");
        int offset = Integer.valueOf(ToolUtil.isEmpty(offset2) ? "0" : offset2);   //每页的偏移量(本页当前有多少条)-就是启始行号

        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)

        if (ToolUtil.isEmpty(sort)) {
            //排序字段为空时
            Page<T> page = new Page<>((offset / limit + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            //排序字段不为空时
            Page<T> page = new Page<>((offset / limit + 1), limit, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
}
