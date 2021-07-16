package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.CommentBean;
import com.boot.shop.bean.P_CBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface P_CMapper extends BaseMapper<P_CBean> {
    //查找pid产品对应的所有的内容
    //这里返回的是pid号对应的pid和cmid（评论id）
    @Select("select tbl_comment.* from tbl_product_comment left join tbl_comment on tbl_product_comment.cmid = tbl_comment.id where pid=#{pid}")
    List<CommentBean> getComment(@Param("pid")int pid);
}
