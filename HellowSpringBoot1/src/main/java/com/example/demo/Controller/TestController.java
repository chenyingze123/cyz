package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.AdminRepository;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("admin")
public class TestController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @RequestMapping("/list")
//    public Map<String, Object> list() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("list", adminService.findAll());
//        return map;
//    }
    @Autowired
    private AdminService adminService;
   @RequestMapping("/list")
    public List<Admin> list() {
        return adminService.findAll();
}
    
    @RequestMapping("/test/{username}")
    public Admin getAllUser(@PathVariable("username") String username){
        return adminService.findByUsername(username);
        
    }
   @RequestMapping("/test1/{key}")
    public List<Admin>  getAllLikes(@PathVariable("key") String key){
	   if (key != null) {
			return adminService.findByLike(key);
		} else {
			return adminService.findAll();
		}
    }


}
