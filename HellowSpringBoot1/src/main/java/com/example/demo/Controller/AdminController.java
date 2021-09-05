package com.example.demo.Controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/add")
	@ResponseBody
	public String register(Admin admin) {
		// String username=admin.getUsername();
		String username = admin.getUsername();
		Admin admin1 = new Admin();
		admin1 = adminService.findByUsername(username);
		if (admin1 == null) {

			try {
				adminService.save(admin);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		} else {
			return "error1";
		}

		return "success";
	}

	@GetMapping("/delete")
	public void delete(Long id) {
		adminService.deleteById(id);

	}

	@RequestMapping("/edit")

	public String update(Admin admin) {
		try {
			adminService.edit(admin);

		} catch (Exception e) {
			e.printStackTrace();
			// return "error";
		}
		return "success";

	}

	@RequestMapping("/findName")
	public List<Admin> getAllLikes(String key) {
		
		if (key != null) {
			return adminService.findByLike(key);
		} else {
			return adminService.findAll();
		}
	}
}
//}
