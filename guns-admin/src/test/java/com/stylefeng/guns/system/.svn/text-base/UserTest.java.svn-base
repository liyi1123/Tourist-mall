package com.stylefeng.guns.system;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.model.ProductType;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.travel.service.IProductTypeService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMapper userMapper;

    @Resource
    IProductTypeService productTypeService;



    @Test
    public void userTest() throws Exception {
//        User admin = userMapper.getByAccount("admin");
//        System.out.println(admin.toString())

        List<ProductType>  productTypeList = productTypeService.list("景点");
        for(int i=0;i<productTypeList.size();i++ ){
            System.out.println(productTypeList.get(i).toString());
        }
//
//          for(ProductType productType:productTypeList){
//              System.out.println(productType.toString());
//          }
    }

}
