package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Hotel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    List<Hotel> findCompanyCreditByCompanyName(Hotel sysCompany);
}
