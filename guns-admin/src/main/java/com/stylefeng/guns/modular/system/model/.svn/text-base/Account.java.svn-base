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
@TableName("account")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    /**
     * 帐户
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * sys_user的ID
     */
    private Integer sysUserId;
    /**
     * 余额
     */
    private Float money;
    /**
     * 积分
     */
    private Integer integral;


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

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Account{" +
        "id=" + id +
        ", sysUserId=" + sysUserId +
        ", money=" + money +
        ", integral=" + integral +
        "}";
    }
}
