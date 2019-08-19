package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-19 20:30
 */
public class StudentEntity implements Serializable {

    private Long id;
    private String name;
    private byte sex;
    private String address;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
