package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 11264 kB
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-02
 */
@TableName("rent_car")
public class RentCar extends Model<RentCar> {

    private static final long serialVersionUID = 1L;

    /**
     * 租车
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 座位数
     */
    private Integer seatCount;
    /**
     * 车辆品牌
     */
    private Integer carBrandId;
    /**
     * 变速箱
     */
    private String gearBox;
    /**
     * 日租金
     */
    private Float rent;
    /**
     * 租车类型
     */
    private Integer productTypeId;
    /**
     * 介绍
     */
    private String content;
    /**
     * 图片一
     */
    private String img1;

    /**
     * 发布者ID
     */
    private Integer sysUserId;

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Integer carBrandId) {
        this.carBrandId = carBrandId;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public Float getRent() {
        return rent;
    }

    public void setRent(Float rent) {
        this.rent = rent;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg1() {return img1; }

    public void setImg1(String img1) {this.img1 = img1; }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RentCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seatCount=" + seatCount +
                ", carBrandId=" + carBrandId +
                ", gearBox='" + gearBox + '\'' +
                ", rent=" + rent +
                ", productTypeId=" + productTypeId +
                ", content='" + content + '\'' +
                ", img1='" + img1 + '\'' +
                ", sysUserId=" + sysUserId +
                '}';
    }
}
