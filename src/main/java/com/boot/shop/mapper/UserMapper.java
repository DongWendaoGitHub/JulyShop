package com.boot.shop.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.UserBean;

//接口继承接口，继承BaseMapper之后，就可以实现简单的增删改查
//复杂的增删改查，就只能写SQL语句
public interface UserMapper extends BaseMapper<UserBean>{
	
	//通过用户名或密码去tbl_user表里找这个人，如果找到则登录成功
	//否则登录失败，这个查询操作有点复杂，需要写sql语句
	
	//Mybatis注解用法：
	//@Select注解	放查询语句
	//@Insert注解
	//@Delete注解
	//@Update注解
	//Mybatis XML用法，后面再说，写代码比较繁琐，不打算细讲，知道就行了
	
	//调用这个抽象函数，就相当于执行了纸条SQL语句
	//绑定参数，方法形参要绑定到占位符的地方，即？的地方
	//#{}是取出参数的意思
	@Select("select * from tbl_user where username=#{username} and password=#{password}")
	UserBean getUser(@Param("username")String username,@Param("password")String password);
	
	
}
