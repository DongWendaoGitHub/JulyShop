package com.boot.shop.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.HotBean;
import com.boot.shop.bean.ProductBean;

public interface HotMapper extends BaseMapper<HotBean>{
	//根据类别id查询改类别下的商品列表
		//@Select("select tbl_product.*,tbl_category.category "
			//	+ "from tbl_product left join tbl_category "
				//+ "on tbl_product.cid=tbl_category.id "
				//+ "where cid=#{cid}")
		//List<ProductBean> getProduct(@Param("cid")int cid);
		
	    @Select("select id,product,hot from tbl_product "
	    		+ "where hot=#{hot}")
	    List<ProductBean> getProduct(@Param("hot")int hot);
}

