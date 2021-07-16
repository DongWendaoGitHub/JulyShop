package com.boot.shop.bean;

import java.util.List;

//Bean WxResp：给小程序的数据库格式
public class WxResp {
    public int code = 1;//相应码，code=1代表成功；code=0代表失败，默认成功
    public String msg = "";//错误提示语，默认没有错误

    public void fail(String msg){
        this.code = 0;//失败
        this.msg = msg;//失败有了错误提示语
    }

    public List<CategoryBean> category;//类别数组
    public List<ProductBean> product;//商品数组
    public List<ProductBean> hot;//热卖数组

    public List<CommentBean> comment;//评论数组
    public List<OrderBean> order;//订单表
    public UserBean user;//登录用户
}


