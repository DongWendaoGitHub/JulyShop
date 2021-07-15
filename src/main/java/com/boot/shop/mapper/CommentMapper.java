package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.CommentBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<CommentBean> {
    //根据用户id编号查询用户评论
    @Select("select * from tbl_comment where tbl_comment.uid = #{uid}")
    List<CommentBean> getComment(@Param("uid")int uid);
}
