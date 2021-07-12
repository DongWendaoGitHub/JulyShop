package com.boot.shop.controller;

import com.boot.shop.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
    @Autowired
    private CategoryMapper categoryMapper;

    //localhost:8080/category/list
    @RequestMapping("/list")
    public String list(){
        System.out.println("查询操作");
        return "";
    }

    @RequestMapping("/add")
    public  String add(){
        System.out.println("添加操作");
        return "";
    }

    @RequestMapping("/del")
    public String del(){
        System.out.println("删除操作");
        return "";
    }
}
