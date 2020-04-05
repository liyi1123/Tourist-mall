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
 * @since 2018-07-30
 */
@TableName("product_m_theme")
public class ProductMTheme extends Model<ProductMTheme> {

    private static final long serialVersionUID = 1L;

    /**
     * 各产品&主题的中间表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 主题ID
     */
    private Integer themeId;
    /**
     * 关联表名
     */
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductMTheme{" +
        "id=" + id +
        ", productId=" + productId +
        ", themeId=" + themeId +
        ", type=" + type +
        "}";
    }
}
