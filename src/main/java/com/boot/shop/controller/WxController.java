package com.boot.shop.controller;

import com.boot.shop.bean.OrderBean;
import com.boot.shop.bean.ProductBean;
import com.boot.shop.bean.ShoppingBean;
import com.boot.shop.bean.WxResp;
import com.boot.shop.mapper.CategoryMapper;
import com.boot.shop.mapper.OrderMapper;
import com.boot.shop.mapper.ProductMapper;
import com.boot.shop.mapper.ShoppingMapper;
import com.boot.shop.util.NotNull;
import com.boot.shop.util.NotNullUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController{
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShoppingMapper shoppingMapper;
    //locathost:8080/wx/index,这个地址浏览器可以访问，小程序可以访问
    @GetMapping("/index")
    public void index(Integer cid, HttpServletResponse response){
        WxResp r = new WxResp();
        r.category = categoryMapper.selectList(null);
        if(!r.category.isEmpty()){
            r.product = cid !=null ?productMapper.getProduct(cid) : productMapper.getProduct(r.category.get(0).getId());
        }
        r.hot = productMapper.getHot();
        outRespJson(r,response);//可以吧一个对象转换成json字符串输出到浏览器或小程序中
    }

    @PostMapping("/order")
    public void order(OrderBean bean,HttpServletResponse response){
//        System.out.println(bean.getJson());
        //json字符串转对象或数组
        //固定写法,使用谷歌研发的geon jar包
        List<ProductBean> product = new Gson().fromJson(bean.getJson(),
                new TypeToken<List<ProductBean>>(){}.getType());

        WxResp r = new WxResp();
        String alert = NotNullUtil.isBlankAlert(bean);
        if(alert!=null){//如果alert不等于空，就说明有错误
            r.fail(alert);//失败了
        }else if(product.isEmpty()){
            r.fail("购物车里空空如也");
        } else {//否则成功
            bean.setCtime(new Date());//当前时间就是下单时间
            orderMapper.insert(bean);//把新订单添加到订单表中
//            System.out.println(bean.getId());//新增订单的id=oid
            for(ProductBean p : product){
                ShoppingBean s = new ShoppingBean(bean.getId(),p.getId(),p.getCount());
                shoppingMapper.insert(s);
            }
        }
        outRespJson(r,response);
    }
}
