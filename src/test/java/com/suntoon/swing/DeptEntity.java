package com.suntoon.swing;

/**
 * @ProjectionName desktop
 * @ClassName DeptEntity
 * @Description 部门实体
 * @Author YueLifeng
 * @Date 2019/4/24 0024上午 10:46
 * @Version 1.0
 */
public class DeptEntity {

    //主键0
    private Integer id;

    //代码
    private String code;

    //名称
    private String name;

    //嵌套对象调用测试
    private TestEntity entity;

    public TestEntity getEntity() {
        return entity;
    }

    public void setEntity(TestEntity entity) {
        this.entity = entity;
    }

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
}
