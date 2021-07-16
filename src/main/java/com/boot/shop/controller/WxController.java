package com.boot.shop.controller;

import com.boot.shop.bean.*;
import com.boot.shop.mapper.*;
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

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private P_CMapper p_cMapper;

    @Autowired
    private UserMapper userMapper;
    //locathost:8080/wx/index,这个地址浏览器可以访问，小程序可以访问
    @GetMapping("/index")
    public void index(Integer cid, HttpServletResponse resp){
        WxResp r = new WxResp();
        r.category = categoryMapper.selectList(null);
        if(!r.category.isEmpty()){
            r.product = cid !=null ?productMapper.getProduct(cid) : productMapper.getProduct(r.category.get(0).getId());
        }
        r.hot = productMapper.getHot();
        outRespJson(r,resp);//可以吧一个对象转换成json字符串输出到浏览器或小程序中
    }

    @PostMapping("/order")
    public void order(OrderBean bean,HttpServletResponse resp){
//        System.out.println(bean.getJson());
        //json字符串转对象或数组
        //固定写法,使用谷歌研发的gson jar包
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
        outRespJson(r,resp);
    }

    @GetMapping("/login")
    public void login(String username,String password,HttpServletResponse resp){
        WxResp r = new WxResp();
        r.user = userMapper.getUser(username, password);
        if(r.user==null){
            r.fail("用户名或密码错误");
        }
        outRespJson(r,resp);//返回登录信息，如果code=1的话代表登录成功，如果code=0需要从r中调用错误信息返回。
    }

    //通过传递用户uid，查询用户的订单信息
    @GetMapping("/order")
    public void order(Integer uid, HttpServletResponse resp){
        WxResp r = new WxResp();
        r.order = orderMapper.getOrder(uid);//获得对应uid的用户信息
        if(r.order.isEmpty()){
            r.fail("找不到该用户！！！");
        }
        outRespJson(r,resp);
    }

    //通过传递pid，查询产品对应评论信息
    @GetMapping("/comment")
    public void comment(int pid,HttpServletResponse resp){
        WxResp r = new WxResp();
        r.comment = p_cMapper.getComment(pid);//拿到pid对应的评论
        if(r.comment.isEmpty()){
            r.fail("评论空空如也");
        }
        outRespJson(r,resp);
    }

    @GetMapping("/product")
    public void product(HttpServletResponse resp){
        WxResp r = new WxResp();
        r.product = productMapper.getProductSale();
        System.out.println(r.product);
        if(r.product.isEmpty()){
            r.fail("无商品！！！");
        }
        outRespJson(r,resp);
    }
}
