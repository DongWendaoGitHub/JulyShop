package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.OrderBean;
import com.boot.shop.bean.ProductBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//BaseMapper<T>里面的泛型T，只有在调用累死selectList（null）才会起作用
//也就是说只有在调用mybatisplus封装的函数才会起作用
public interface OrderMapper extends BaseMapper<OrderBean> {
    @Select("select tbl_product.*, tbl_shopping.count from tbl_shopping "
            + "left join tbl_product on tbl_shopping.pid = tbl_product.id "
            + "where oid=#{oid}")
    List<ProductBean> getProduct(@Param("oid")int oid);
}
