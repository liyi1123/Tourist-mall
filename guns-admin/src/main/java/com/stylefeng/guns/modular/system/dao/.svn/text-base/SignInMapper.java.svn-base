package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.SignIn;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author maolinlong123
 * @since 2018-10-30
 */
public interface SignInMapper extends BaseMapper<SignIn> {

    /**
     *
     * @return 签到任务列表
     */
    List<Map<String,Object>> querySignInList(@Param("subjectName") String subjectName);

    List<Map<String,Object>> stuSignInList(@Param("deptId")Integer deptId);


}
