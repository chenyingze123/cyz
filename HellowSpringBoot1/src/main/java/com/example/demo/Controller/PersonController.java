package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;

import com.example.demo.service.AddressService;
import com.example.demo.service.AdminService;

@RestController
public class PersonController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/personEdit")

	public String update(Admin admin) {
		  String username = admin.getUsername();
		  String realName = admin.getRealName();
		  String sex = admin.getSex();
		  String telephone = admin.getTelephone();
		  String addressName = admin.getAddressName();
		  String mail = admin.getMail();
		  Admin adminperson;
		  adminperson = adminService.findByUsername(username);
		  Address addressperson;
		  
		  addressperson = addressService.findByaddressName(addressName);
		  if (addressperson == null) {
			  return "error";
		}else {
			 long addressId = addressperson.getAddressId();
			  String addressid = String.valueOf(addressId); 
			  String sex1 = new String("男");
			  String sex0 = new String("女");
			  //String sex0 = "女" ;
			  
			  if (sex1.equals(sex)) {
				  adminperson.setSex("1");	
			}else if (sex0.equals(sex)) {
				  adminperson.setSex("0");
			}
			  adminperson.setRealName(realName);
			  //adminperson.setSex(sex1);
			  adminperson.setTelephone(telephone);
			  adminperson.setAddress(addressid);
			  adminperson.setMail(mail);
			 
			
			try {
				adminService.edit(adminperson);

			} catch (Exception e) {
				e.printStackTrace();
				// return "error";
			}
			return "success";
		}
		 

	}
	
	 @RequestMapping("/person")
	 public Admin getPerson(Admin admin,String username,HttpSession session) {
		 username  = (String) session.getAttribute("username");
		 admin = adminService.findByUsername(username);
		 String sex = admin.getSex();
		 int sex1 ;
		 sex1 = Integer.valueOf(sex).intValue();
		 if (sex1 == 1) {
			admin.setSex("男");
		}else if (sex1 == 0) {
			admin.setSex("女");
		}
		 
		 String address = admin.getAddress();
		 long addressId =  Long.valueOf(address).longValue();
		 Address addressByName = addressService.findByaddressId(addressId);
		 String addressName = addressByName.getAddressName();
		 admin.setAddressName(addressName);
		 
		 return admin;

	 }

	
}
