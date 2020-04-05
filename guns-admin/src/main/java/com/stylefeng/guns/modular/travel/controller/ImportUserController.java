package com.stylefeng.guns.modular.travel.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/import")
public class ImportUserController {

    private static Logger log= Logger.getLogger(ImportUserController.class);

    private String PREFIX = "";

    @RequestMapping("")
    public String getImport(){
        log.info("学生批量导入");

        return "/travel/ImportUser.html";
    }

    @RequestMapping("/downloadTemplate")
    public String getDoownloadTemplate(){

        return null;
    }
}
