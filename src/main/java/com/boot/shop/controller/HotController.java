package com.boot.shop.controller;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.boot.shop.bean.CategoryBean;
import com.boot.shop.bean.HotBean;
import com.boot.shop.bean.ProductBean;
import com.boot.shop.mapper.HotMapper;
import com.boot.shop.mapper.ProductMapper;
import com.boot.shop.util.NotNullUtil;

@Controller
@RequestMapping("/hot")
public class HotController extends BaseController{
	@Autowired
	private HotMapper hotMapper;
	@GetMapping("/list")
	public String list(int hot,HttpServletRequest req){
		req.setAttribute("retList",hotMapper.getProduct(hot));
		return "/hot/list";//转发到这里
	}
	@GetMapping("/add")// 打开页面
	public String add(Integer id,HttpServletRequest req){
		req.setAttribute("bean",hotMapper.selectById(id));
		return "/hot/add";//带bean到add.html中
	}
	@PostMapping("/add")//表单提交
	public String add(HotBean bean,HttpServletResponse resp){
		int hot=bean.getHot();//设置一个变量来记录更新前的hot
		hotMapper.updateById(bean);
		if(hot==bean.getHot())//判断hot是否改变，跳转到该商品hot的页面
			return "redirect:/hot/list?hot="+bean.getHot();
		else
			return "redirect:/hot/list"+(bean.getHot()==0?"?hot=1":"?hot=0");
	}
}


