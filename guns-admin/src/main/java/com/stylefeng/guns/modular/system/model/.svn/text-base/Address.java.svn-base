package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 10240 kB
 * </p>
 *
 * @author maolinlong123
 * @since 2018-11-28
 */
@TableName("address")
public class Address extends Model<Address> {

    private static final long serialVersionUID = 1L;

    /**
     * 买家收货地址表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * sys_user的ID
     */
    private Integer sysUserId;
    /**
     * 收货人姓名
     */
    private String userName;
    /**
     * 电话
     */
    private String phone;
    /**
     * 地址信息
     */
    private String addressInfo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Address{" +
        "id=" + id +
        ", sysUserId=" + sysUserId +
        ", userName=" + userName +
        ", phone=" + phone +
        ", addressInfo=" + addressInfo +
        "}";
    }
}
