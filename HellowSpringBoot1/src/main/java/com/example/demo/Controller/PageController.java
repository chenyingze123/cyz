package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.entity.Admin;
import com.example.demo.service.PageService;



@Controller
@RequestMapping("/page")
public class PageController {

	@Autowired
	private PageService pageService;
	
	 @ResponseBody
	    @RequestMapping("/list") 
	    public Map<String, Object> list(@RequestParam(value = "userLike", required = false) String userLike ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows) {
		     System.out.println("userLike:"+userLike);
	        Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
	     /*   if(userLike == null) {*/
	        	Page<Admin> pageUser = pageService.findAdminByUserLike(userLike, pageable);
		        // ①获取rows
		        List<Admin> list = pageUser.getContent();
		        // ②获取count
		        Long count = pageService.countByUserLike(userLike);
		        Map<String, Object> resultMap = new HashMap<String, Object>();
		        resultMap.put("rows", list);
		        resultMap.put("total", count);
		        resultMap.put("success", true);
		        return resultMap;
	       /* }else {
	        Page<Admin> pageUser = pageService.findAdminByUserLike(userLike, pageable);
	        // ①获取rows
	        List<Admin> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageService.countByUserLike(userLike);
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        System.out.println(list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
	        
	        
	        }*/
	         
	        
	    }
	
	}

