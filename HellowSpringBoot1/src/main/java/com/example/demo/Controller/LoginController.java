package com.example.demo.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.service.AdminService;
import com.example.demo.service.CenterService;
import com.example.demo.service.InstructorService;
import com.example.demo.service.StudentService;

@Controller

public class LoginController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CenterService centerService;
	@Autowired
	private InstructorService instructorService;
	 @RequestMapping("/loginxx")
	    public String LoginAdmin( String username,String password, HttpServletRequest request,String userType) {
	        HttpSession session = request.getSession();
				//String studentType = "student";
	            //String managerType = "manager";
	          
	          if (userType.equals("4")) {//管理员
	        	   Admin admin =new Admin();
	        	   admin = adminService.findByUsername(username);  
	        	   
	        	   if (admin == null) {
		            	
		                return  "login";
		                
		            }
		            
		            
		            if (password.equals(admin.getPassword())){
		                //userModel.setUserType("student");
		                //session.setAttribute("userType", "student");
		                //session.setAttribute("username", adminLogin.getUsername());
		            	//adminLogin=admin;
		                //adminLogin.setRealName(admin.getRealName());
		                //session.setAttribute("adminLogin", adminLogin);
		                session.setAttribute("realname", admin.getRealName());
		                session.setAttribute("username", admin.getUsername());
		                session.setAttribute("userAdmin", admin.getUsername());
		                session.setAttribute("userType", userType);
		                return "redirect:/main";
		                
		            } else {
		                return "login";
		                
		            } 
			  }
	          if (userType.equals("1")) {//学生
	        	  Student student = new Student(); 
	        	   student =studentService.findByUsername(username);
	        	   if (student == null) {
		            	
		                return  "login";
		                
		            }
		            
		            if (password.equals(student.getPassword())){
		            	session.setAttribute("realname", student.getName());
		                session.setAttribute("username", student.getUsername());
		                session.setAttribute("userStu", student.getUsername());
		                session.setAttribute("userType", userType);
		                return "redirect:/stumain";
		                
		            } else {
		                return "login";
		                
		            } 
			  }
	          if (userType.equals("3")) {//辅导员
	        	  Instructor instructor = new Instructor();
	        	  instructor = instructorService.findInstructorByUsername(username);
	        	  
	        	   if (instructor == null) {
		            	
		                return  "login";
		                
		            }
		            
		            if (password.equals(instructor.getPassword())){
		            	session.setAttribute("realname", instructor.getName());
		                session.setAttribute("username", instructor.getUsername());
		                session.setAttribute("userInstr", instructor.getUsername());
		                session.setAttribute("userType", userType);
		                return "redirect:/instrmain";
		                
		            } else {
		                return "login";
		                
		            } 
			  }
	          if (userType.equals("2")) {//任课教师
	        	  /*Instructor instructor = new Instructor();
	        	  instructor = instructorService.findInstructorByUsername(username);*/
	        	  Teacher teacher =new Teacher();
	        	  teacher = centerService.findTeacherByUsername(username);
	        	 // teacher = CenterService.findTeacherByUsername(username);
	        			  
	        	   if (teacher == null) {
		            	
		                return  "login";
		                
		            }
		            
		            if (password.equals(teacher.getPassword())){
		            	session.setAttribute("realname", teacher.getName());
		                session.setAttribute("username", teacher.getUsername());
		                session.setAttribute("userTea", teacher.getUsername());
		                session.setAttribute("userType", userType);
		                return "redirect:/teachermain";
		                
		            } else {
		                return "login";
		                
		            } 
			  }
			return "login";
      
	         
	             
	    }
	/* @RequestMapping(value = "/logout")
	    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request) {
	        request.getSession().invalidate();
	        modelAndView.setViewName("student/student_login");
	        return modelAndView;
	    }
	    
	*/
	 @GetMapping("/logout")
	    public String logout(HttpSession session){
	        session.removeAttribute("realname");
	        session.removeAttribute("username");
	        session.removeAttribute("userStu");
	        session.removeAttribute("userAdmin");
	        session.removeAttribute("userInstr");
	        session.removeAttribute("userTea");
	        session.removeAttribute("userType");
	        //session.removeAttribute("adminLogin");
	        return "redirect:/login";
	    }
		
	    }



