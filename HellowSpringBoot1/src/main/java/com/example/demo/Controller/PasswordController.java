package com.example.demo.Controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

@RestController
public class PasswordController {
	@Autowired
	private AdminService adminService;
	@PostMapping("/editpwd")
	 public String Change(String oldPassword, String newPassword, HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
        Admin adminpwd = adminService.findByUsername(username);
        if (!Objects.equals(adminpwd.getPassword(), oldPassword)) {
            return "old error";
        } else {
        	adminpwd.setPassword(newPassword);
            adminService.save(adminpwd);
            return "success";
        }
        
         
     }
}
