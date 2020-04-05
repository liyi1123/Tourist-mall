package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 9216 kB
 * </p>
 *
 * @author maolinlong123
 * @since 2018-08-06
 */
@TableName("visa")
public class Visa extends Model<Visa> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 签证国家ID
     */
    private Integer visaNationId;
    /**
     * 签证类型ID
     */
    private Integer visaTypeId;
    /**
     * 价格
     */
    private Float price;
    /**
     * 签发城市
     */
    private  Integer issueAt;
    /**
     * 有效期
     */
    private String usefulLife;
    /**
     * 办理用时
     */
    private String needTime;
    /**
     * 最长停留时间
     */
    private String maxDurationOfStay;
    /**
     * 所需材料
     */
    private String needDatum;
    /**
     * 介绍
     */
    private String recommend;
    /**
     * 发布者ID
     */
    private Integer sysUserId;


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

    public Integer getVisaNationId() {
        return visaNationId;
    }

    public void setVisaNationId(Integer visaNationId) {
        this.visaNationId = visaNationId;
    }

    public Integer getVisaTypeId() {
        return visaTypeId;
    }

    public void setVisaTypeId(Integer visaTypeId) {
        this.visaTypeId = visaTypeId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(Integer issueAt) {
        this.issueAt = issueAt;
    }

    public String getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(String usefulLife) {
        this.usefulLife = usefulLife;
    }

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime;
    }

    public String getMaxDurationOfStay() {
        return maxDurationOfStay;
    }

    public void setMaxDurationOfStay(String maxDurationOfStay) {
        this.maxDurationOfStay = maxDurationOfStay;
    }

    public String getNeedDatum() {
        return needDatum;
    }

    public void setNeedDatum(String needDatum) {
        this.needDatum = needDatum;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Visa{" +
        "id=" + id +
        ", name=" + name +
        ", visaNationId=" + visaNationId +
        ", visaTypeId=" + visaTypeId +
        ", price=" + price +
        ", issueAt=" + issueAt +
        ", usefulLife=" + usefulLife +
        ", needTime=" + needTime +
        ", maxDurationOfStay=" + maxDurationOfStay +
        ", needDatum=" + needDatum +
        ", recommend=" + recommend +
        ", sysUserId=" + sysUserId +
        "}";
    }
}
