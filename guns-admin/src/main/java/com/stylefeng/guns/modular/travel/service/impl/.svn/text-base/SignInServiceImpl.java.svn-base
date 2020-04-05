package com.stylefeng.guns.modular.travel.service.impl;

import com.stylefeng.guns.modular.system.model.SignIn;
import com.stylefeng.guns.modular.system.dao.SignInMapper;
import com.stylefeng.guns.modular.travel.service.ISignInService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 8192 kB 服务实现类
 * </p>
 *
 * @author maolinlong123
 * @since 2018-10-30
 */
@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn> implements ISignInService {


    @Autowired
    private SignInMapper signInMapper;

    @Override
    public List<Map<String, Object>> querySignInList(String subjectName) {
        return signInMapper.querySignInList(subjectName);
    }

    @Override
    public List<Map<String, Object>> stuSignInList(Integer deptId) {
        return signInMapper.stuSignInList(deptId);
    }
}
