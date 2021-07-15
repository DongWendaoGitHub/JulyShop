package com.boot.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//一个Bean对应一张表
//UserBean对应tbl_user表
//表里有什么，Bean里面就有什么。
//mybatisplus要求我们这么做，我们会得到简单的增删改查不需要写SQL语句。

@TableName("tbl_user")
public class UserBean {
	//id是主键
	@TableId(value="id",type=IdType.AUTO)//auto_increment自动增长
	private Integer id;//尽量使用Bean里面的Integer来替代int
	//int不能接受null类型
	//Integer可以接受null类型
	private String username;
	private String password;


	//生成set、get方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private String address;
	private String mobile;
	private int ulimit;
	private int consume;
	private double discount;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getLimit() {
		return ulimit;
	}

	public void setLimit(int limit) {
		this.ulimit = limit;
	}

	public int getConsume() {
		return consume;
	}

	public void setConsume(int consume) {
		this.consume = consume;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
