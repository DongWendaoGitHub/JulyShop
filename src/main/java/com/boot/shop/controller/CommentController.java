package com.boot.shop.controller;

import com.boot.shop.bean.CommentBean;
import com.boot.shop.mapper.CommentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/list")
    public String list(String uid, HttpServletRequest request){
        request.setAttribute("uid",uid);
        request.setAttribute("retList", StringUtils.isBlank(uid) ?
                commentMapper.selectList(null):
                commentMapper.getComment(Integer.valueOf((uid))));
        return "/comment/list";
    }
    @GetMapping("/del")
    public String del(int id){
        commentMapper.deleteById(id);
        return "/comment/list";
    }
}
