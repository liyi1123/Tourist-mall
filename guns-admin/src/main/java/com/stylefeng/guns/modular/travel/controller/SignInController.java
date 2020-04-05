package com.stylefeng.guns.modular.travel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Dept;
import com.stylefeng.guns.modular.system.model.SignIn;
import com.stylefeng.guns.modular.system.service.IDeptService;
import com.stylefeng.guns.modular.travel.service.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author maolinlong
 * @Date 2018-10-30 17:23:20
 */
@Controller
@RequestMapping("/signIn")
public class SignInController extends BaseController {

    private String PREFIX = "/travel/";

    @Autowired
    private ISignInService signInService;

    @Autowired
    private IDeptService deptService;
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "signIn.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/signIn_add")
    public String signInAdd() {
        return PREFIX + "signIn_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/signIn_update/{signInId}")
    public String signInUpdate(@PathVariable Integer signInId, Model model) {
        SignIn signIn = signInService.selectById(signInId);
        Dept dept = deptService.selectById(signIn.getDeptId());
        model.addAttribute("item", signIn).addAttribute("deptName", dept.getSimplename());
        LogObjectHolder.me().set(signIn);
        return PREFIX + "signIn_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        return signInService.querySignInList(condition);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SignIn signIn) {
        signInService.insert(signIn);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam(value = "signInIds[]") Integer[] signInIds) {

        try {
            if(null != signInIds && signInIds.length > 0){
                        for(int i=0;i<signInIds.length;i++){
                            signInService.deleteById(signInIds[i]);
                            EntityWrapper<SignIn> entityWrapper =new EntityWrapper<>();
                            entityWrapper.where("parentId = {0}",signInIds[i]);
                            signInService.delete(entityWrapper);
                        }
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SignIn signIn) {
        signInService.updateById(signIn);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{signInId}")
    @ResponseBody
    public Object detail(@PathVariable("signInId") Integer signInId) {
        return signInService.selectById(signInId);
    }




    /**
     * 跳转到学生签到页面
     */
    @RequestMapping("/goSignIn")
    public String goSignIn(@PathVariable("studentId") Integer studentId) {



        return PREFIX + "signIn_add.html";
    }

    /**
     * 加载该用户相关的课程列表
     */
    @RequestMapping("/lessons")
    public String lessons() {
        //1. 得到该用户的部门ID
        Integer deptId = ShiroKit.getUser().getDeptId();


        return PREFIX + "stu_signIn.html";

    }

    /**
     * ajax 修改签到任务开启/关闭状态
     * @param signIn
     * @return
     */
    @RequestMapping("/changeStatus")
    @ResponseBody
    public Object changeStatus(SignIn signIn) {
        return signInService.updateById(signIn);
    }

    /**
     * 学生签到 拿到签到任务ID,增加此学生签到记录:status设为3
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/addSignIn")
    @ResponseBody
    public Object addSignIn(Integer parentId) {
        ShiroUser user = ShiroKit.getUser();
        SignIn lessons = signInService.selectById(parentId);
        SignIn s = new SignIn(null,lessons.getId(),lessons.getSubjectName(),user.getId(),lessons.getTeacherId(),System.currentTimeMillis() / 1000,null,3,user.getDeptId(),user.getName() + ":已签到");
        return signInService.insert(s);
    }



    /**
     * 获取此学生的班级(部门) 的所有签到任务,如果此学生已经签过到,则把任务状态变更为3(已签到)让前台展示
     */
    @RequestMapping(value = "/stuSignInlist")
    @ResponseBody
    public Object stuSignInlist() {
        ShiroUser user = ShiroKit.getUser();
        List<Map<String, Object>> mapList = signInService.stuSignInList(user.getDeptId());
        for (int i = 0; i < mapList.size(); i++) {
            EntityWrapper<SignIn> signInEntityWrapper = new EntityWrapper<>();
            signInEntityWrapper.where("parentId = {0} and studentId = {1} and `status` = {2}",mapList.get(i).get("id"),user.getId(),3);
            List<SignIn> signInList = signInService.selectList(signInEntityWrapper);
            if(signInList.size() > 0){
                mapList.get(i).put("status",3);
            }
        }
        return mapList;
    }


    /**
     * 跳转到首页
     */
    @RequestMapping("/goHasSignInList")
    public String goHasSignInList() {
        return PREFIX + "hasSignInList.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/hasSignInList",method = RequestMethod.POST)
    @ResponseBody
    public Object hasSignInList(Integer parentId,String condition){
        EntityWrapper<SignIn> entityWrapper=new EntityWrapper();
        if(ToolUtil.isEmpty(condition)){
            entityWrapper.where("parentId = {0}",parentId);
        }else {
            entityWrapper.where("parentId = {0}", parentId).and().like("subjectName", condition).or().like("desc2", condition);
        }

        return  signInService.selectList(entityWrapper);

    }



}
