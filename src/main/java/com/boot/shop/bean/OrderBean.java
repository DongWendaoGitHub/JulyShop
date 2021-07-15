package com.boot.shop.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.shop.util.NotNull;

@TableName("tbl_order")
public class OrderBean {
    @TableField(exist=false)//数据表里面没有json字段
    private String json;
    public String getJson() {
        return json;
    }
    public void setJson(String json) {
        this.json = json;
    }
    @TableId(value="id",type=IdType.AUTO)
    private Integer id;
    @NotNull(fieldName="用户")
    private Integer uid;
    @NotNull(fieldName="总价")
    private Integer total;

    private Date ctime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Date getCtime() {
        return ctime;
    }
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }


}
