package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.dao.CompanyMapper;
import com.stylefeng.guns.modular.system.model.Hotel;
import com.stylefeng.guns.modular.system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper  companyMapper;

    @Override
    public List<Hotel> findCompanyCreditByCompanyName(Hotel sysCompany) {
        return companyMapper.findCompanyCreditByCompanyName(sysCompany);
    }

}
