package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 6144 kB
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-17
 */
@TableName("scenic_spot")
public class ScenicSpot extends Model<ScenicSpot> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 景点名称
     */
    private String name;
    /**
     * 级别
     */
    private String grade;
    /**
     * 地区ID
     */
    private Integer regionId;
    /**
     * 产品类型Id
     */
    private Integer productTypeId;
    /**
     * 成人票价
     */
    private Float adultTicket;
    /**
     * 儿童票价
     */
    private Float childrenTicket;
    /**
     * 特殊票价
     */
    private Float specialTiclet;
    /**
     * 地址
     */
    private String address;
    /**
     * 开放时间
     */
    private String openTime;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 服务承诺
     */
    private String commitment;
    /**
     * 经度X
     */
    @TableField("map_x")
    private String mapX;
    /**
     * 维度Y
     */
    @TableField("map_y")
    private String mapY;
    /**
     * 推荐
     */
    private Integer recommend;
    /**
     * 热点
     */
    private Integer hot;
    /**
     * 图片1
     */
    private String img1;
    /**
     * 图片2
     */
    private String img2;
    /**
     * 图片3
     */
    private String img3;
    /**
     * 图片4
     */
    private String img4;
    /**
     * 图片5
     */
    private String img5;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Float getAdultTicket() {
        return adultTicket;
    }

    public void setAdultTicket(Float adultTicket) {
        this.adultTicket = adultTicket;
    }

    public Float getChildrenTicket() {
        return childrenTicket;
    }

    public void setChildrenTicket(Float childrenTicket) {
        this.childrenTicket = childrenTicket;
    }

    public Float getSpecialTiclet() {
        return specialTiclet;
    }

    public void setSpecialTiclet(Float specialTiclet) {
        this.specialTiclet = specialTiclet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCommitment() {
        return commitment;
    }

    public void setCommitment(String commitment) {
        this.commitment = commitment;
    }

    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ScenicSpot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", regionId=" + regionId +
                ", productTypeId=" + productTypeId +
                ", adultTicket=" + adultTicket +
                ", childrenTicket=" + childrenTicket +
                ", specialTiclet=" + specialTiclet +
                ", address='" + address + '\'' +
                ", openTime='" + openTime + '\'' +
                ", introduce='" + introduce + '\'' +
                ", commitment='" + commitment + '\'' +
                ", mapX='" + mapX + '\'' +
                ", mapY='" + mapY + '\'' +
                ", recommend=" + recommend +
                ", hot=" + hot +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                ", img4='" + img4 + '\'' +
                ", img5='" + img5 + '\'' +
                ", sysUserId=" + sysUserId +
                '}';
    }
}
