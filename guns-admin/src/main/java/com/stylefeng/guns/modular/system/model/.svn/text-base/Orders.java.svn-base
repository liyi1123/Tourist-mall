package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单和订单详情表; InnoDB free: 10240 kB
 * </p>
 *
 * @author maolinlong123
 * @since 2018-11-27
 */
@TableName("orders")
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父ID;0-订单;大于0-订单详情
     */
    private Integer parentId;
    /**
     * 订单类型:1景点;2酒店;3租车;4签证
     */
    private Integer orderType;
    /**
     * 对应的产品ID
     */
    private Integer productId;
    /**
     * 买家ID
     */
    private Integer buyerId;
    /**
     * 收货地址表ID
     */
    private Integer addressId;
    /**
     * 订单编号
     */
    private String orderNum;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 最晚到店时间
     */
    private Date lastTime;
    /**
     * 入住时间
     */
    private Date checkInTime;
    /**
     * 离店时间
     */
    private Date checkOutTime;
    /**
     * 总金额
     */
    private Float totalMoney;
    /**
     * 使用积分
     */
    private Integer usingIntegral;
    /**
     * 备注
     */
    private String desc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getUsingIntegral() {
        return usingIntegral;
    }

    public void setUsingIntegral(Integer usingIntegral) {
        this.usingIntegral = usingIntegral;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Orders{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", orderType=" + orderType +
        ", productId=" + productId +
        ", buyerId=" + buyerId +
        ", addressId=" + addressId +
        ", orderNum=" + orderNum +
        ", number=" + number +
        ", lastTime=" + lastTime +
        ", checkInTime=" + checkInTime +
        ", checkOutTime=" + checkOutTime +
        ", totalMoney=" + totalMoney +
        ", usingIntegral=" + usingIntegral +
        ", desc=" + desc +
        "}";
    }
}
