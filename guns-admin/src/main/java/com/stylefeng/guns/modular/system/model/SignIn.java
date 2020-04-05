package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 8192 kB
 * </p>
 *
 * @author maolinlong123
 * @since 2018-10-30
 */
@TableName("sign_in")
public class SignIn extends Model<SignIn> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父级ID
     */
    private Integer parentId;
    /**
     * 课目名称
     */
    private String subjectName;
    /**
     * 学生ID
     */
    private Integer studentId;
    /**
     * 老师ID
     */
    private Integer teacherId;
    /**
     * 开始时间
     */
    private Long beginTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 状态:0 关闭,1开启
     */
    private Integer status;
    /**
     * 部门ID
     */
    private Integer deptId;



    /**

     * 备用字段2
     */
    private String desc2;


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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }


    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SignIn{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", subjectName='" + subjectName + '\'' +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", deptId=" + deptId +
                ", desc2='" + desc2 + '\'' +
                '}';
    }

    public SignIn() {
    }

    public SignIn(Integer id, Integer parentId, String subjectName, Integer studentId, Integer teacherId, Long beginTime, Long endTime, Integer status, Integer deptId, String desc2) {
        this.id = id;
        this.parentId = parentId;
        this.subjectName = subjectName;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.status = status;
        this.deptId = deptId;
        this.desc2 = desc2;
    }
}
