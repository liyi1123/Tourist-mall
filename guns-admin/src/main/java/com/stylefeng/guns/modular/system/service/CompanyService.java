package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.Hotel;

import java.util.List;

public interface CompanyService {
    List<Hotel> findCompanyCreditByCompanyName(Hotel sysCompany);
}
