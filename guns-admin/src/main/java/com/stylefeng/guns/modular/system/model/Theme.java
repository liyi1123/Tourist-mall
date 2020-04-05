package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 7168 kB
 * </p>
 *
 * @author maolinlong123
 * @since 2018-07-16
 */
@TableName("theme")
public class Theme extends Model<Theme> {

    private static final long serialVersionUID = 1L;

    /**
     * 主题
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 热门主题
     */
    private String name;


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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Theme{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
