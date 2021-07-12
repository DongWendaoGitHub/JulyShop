package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.CategoryBean;
import com.boot.shop.controller.BaseController;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends BaseMapper<CategoryBean> {
    //增删改查不需要写SQL语句，Mapper层以及继承关系必须要有

}
