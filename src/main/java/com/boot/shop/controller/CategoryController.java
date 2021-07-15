package com.boot.shop.controller;

import com.boot.shop.bean.CategoryBean;
import com.boot.shop.mapper.CategoryMapper;
import com.boot.shop.mapper.ProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    //localhost:8080/category/list
    @RequestMapping("/list")
    public String list(String category, HttpServletRequest request){
        request.setAttribute("category",category);
        request.setAttribute("retList",StringUtils.isBlank(category) ?
                categoryMapper.selectList(null):
                categoryMapper.getLike("%"+category+"%"));


        return "/category/list";
    }
    //  <a>是超链接，可以打开一个页面
    //  还可以执行一段js代码
    //  因为可以打开页面，所以是get请求
    //  因为超链接会改变浏览器地址，所以它是重定向
    //  form表单是post请求，form表单提交是post请求，参考登录
    //  @RequetsMapping=@GetMapping+@PostMapping

    //localhost:8080/category/add
    //添加和修改都访问/category/add，节约代码量
    @GetMapping("/add")//打开页面
    public String add(Integer id,HttpServletRequest request){
        request.setAttribute("bean",(id != null ?categoryMapper.selectById(id):null));
        return "/category/add";//带bean到add.html中
    }
    @PostMapping("/add")//表单提交
    public String add(CategoryBean bean, HttpServletResponse resp){
        if (StringUtils.isBlank(bean.getCategory())){
            return jsAlert("请输入类别名称",
                    ("/category/add" + (bean.getId()!=null?"?id="+bean.getId():"")),
                    resp);
        }
        try {
            if (bean.getId()!=null){
                categoryMapper.updateById(bean);
            }else{
                categoryMapper.insert(bean);
            }
        }catch (Exception e){
            return jsAlert(bean.getCategory() + "已经存在了！",
                    ("/category/add" + (bean.getId()!=null?"?id="+bean.getId():"")),
                    resp);//resp是响应，要把数据响应给页面
        }
        return "redirect:/category/list";
    }
    //localhost:8080/category/del
    @RequestMapping("/del")
    public String del(int id){
        categoryMapper.deleteById(id);
        //假如没有外键的级联操作
        //再去删去商品表中对应类别的商品

        return "redirect:/category/list";//重新查询列表数据
    }
}
