package com.boot.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
@MapperScan("com.boot.shop.mapper")
public class App implements WebMvcConfigurer {

    //需求分析：分析用户需求，登录、注册、类别管理、商品管理、购物车、热卖管理
    //退出登录、提交订单、价格核算、我的历史订单查询、我用过的地址查询
    //我的钱包（充钱（假的）！），支付（假的）

    //项目搭建：Mysql怎么玩的，为什么要用Mysql？（免费啊……）
    //        图形工具，Navicat HeidiSQL
    //        JDK简单介绍
    //          SpringBoot，MyBatis，MyBatisPlus，为什么要用
    //          怎么搭建SpringBoot项目？
    //数据库设计：字段、外键，数据表……
    //代码设计：Controller，Mapper，Bean，HTML+Freemaker语法怎么用的啊【后台】
    //      小程序、js、wxml、wxss、json，还有一些swiper
    //  各类电商（不能和老师一样），点餐，图书馆
    //  一台电脑是java，启动成功，另一台电脑是小程序打开，再找一个手机打开热点
    // 让这两台电脑连接一个热点，使用cmd命令查看ipconfig ip地址
    // 小程序修改api的请求的ip地址，改成http://xxx.xxx.xxx.xxx:8080
    // 客户端和服务端分离

    //4位同学，两位java端，两位小程序端。文档所有都参与进来
    // 主函数，整个项目的启动入口
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }

    @Override
    // 上传图片的地址映射
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/shop/**").addResourceLocations("file:E:/CODE/create/shop/"); //修改本地文件路径
    }

}
