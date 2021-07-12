package com.boot.shop.mapper;

import com.boot.shop.bean.CategoryBean;
import com.boot.shop.controller.BaseController;
import org.beetl.sql.core.mapper.BaseMapper;

public interface CategoryMapper extends BaseMapper<CategoryBean> {
    //增删改查不需要写SQL语句，Mapper层以及继承关系必须要有

}
