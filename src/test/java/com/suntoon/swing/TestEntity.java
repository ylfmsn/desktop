package com.suntoon.swing;

import java.util.Date;

/**
 * @ProjectionName desktop
 * @ClassName TestEntity
 * @Description 测试用的实体类
 * @Author YueLifeng
 * @Date 2019/4/24 0024上午 10:44
 * @Version 1.0
 */
public class TestEntity {

    //主键
    private Integer id;

    //编码
    private String code;

    //名称
    private String name;

    //性别
    private String gender;

    //生日
    private Date birthday;

    //年龄
    private Integer age;

    //所属部门
    private DeptEntity dept;

    //婚姻状况
    private Integer marryState;

    //最后登入时间
    private Date lastLogin;

    //是否在职
    private Boolean onDuty;

    //角色
    private Integer role;

    //颜色
    private Integer color;

    //备注
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public DeptEntity getDept() {
        return dept;
    }

    public void setDept(DeptEntity dept) {
        this.dept = dept;
    }

    public Integer getMarryState() {
        return marryState;
    }

    public void setMarryState(Integer marryState) {
        this.marryState = marryState;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(Boolean onDuty) {
        this.onDuty = onDuty;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "id=" + id + " code=" + code + " name=" + name;
    }
}
