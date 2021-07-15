package com.boot.shop.controller;

import com.boot.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/list")
    public String list(HttpServletRequest request){
        request.setAttribute("retList",orderMapper.selectList(null));
        return "/order/list";

    }
    @GetMapping("/product")
    public String product(int oid,HttpServletRequest request){
        request.setAttribute("bean",orderMapper.selectById(oid));
        request.setAttribute("retList",orderMapper.getProduct(oid));
        return "order/product";
    }
}
