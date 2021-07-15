package com.boot.shop.controller;

import com.boot.shop.bean.ProductBean;
import com.boot.shop.mapper.ProductMapper;
import com.boot.shop.util.NotNullUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/list")
    public String list(int cid , HttpServletRequest request){
        request.setAttribute("retList",productMapper.getProduct(cid));
        request.setAttribute("cid",cid);
        return "/product/list";
    }

    @GetMapping("/del")
    public String del(int id,int cid){
        productMapper.deleteById(id);
        return  "redirect:/product/list?cid="+cid;//
    }

    @GetMapping("/add")
    public String add(Integer id,int cid,HttpServletRequest request){
        request.setAttribute("cid",cid);
        request.setAttribute("bean",(id!=null ? productMapper.selectById(id) : null));
        return "/product/add";
    }

    @PostMapping("/add")
    public String add(ProductBean bean,HttpServletResponse response) {
        if (NotNullUtil.isBlank(bean)){
            //如果有@NotNull注解的属性，不管是哪个属性，只要发现值是空的，就会为true
            return jsAlert("请完善商品信息",
                    ("/product/add?cid=" + bean.getCid() +
                            (bean.getId()!=null?"&id="+bean.getId():"")),
                    response);
        }
        //简单的处理整型负数
        bean.setPrice(Math.abs(bean.getPrice()));
        bean.setNum(Math.abs(bean.getNum()));

        if(bean.getId()!=null){
            productMapper.updateById(bean);
        }else{
            productMapper.insert(bean);
        }
        return "redirect:/product/list?cid=" + bean.getCid();
    }

    //localhost:8080/product/logo
    @RequestMapping("/logo")
    public void logo(MultipartFile file, HttpServletResponse response){
        String fileName =  file.getOriginalFilename();
        try {
            file.transferTo(new File("E:/CODE/create/shop/file/"+fileName));
        }catch (Exception e){
            System.out.println("找不到文件路径E:/CODE/create/shop/file/");
        }
        //输出图片路径给页面，少了前面的，为什么少了这个东西？
        outRespJson("/shop/file/"+fileName,response);
    }
}
