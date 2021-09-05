package com.example.demo.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.MyHttpSessionListener;
import com.example.demo.entity.Admin;
import com.example.demo.entity.LGclass;
import com.example.demo.service.AdminService;
import com.example.demo.service.CenterService;

@Controller
public class HelloController {

//    @RequestMapping(value = "/hello")
//    public String index(){
//        return "Hello World111!";
//    }
	  @Autowired
	    private AdminService adminService;
	  @Autowired
	    private CenterService CenterService;
	  @Value("${application.message:Hello World}")
	    private String message ;

	    @GetMapping("/asd/{name}")
	    public String welcome(@PathVariable String name,Map<String, Object> model) {
	        model.put("time", new Date());
	        model.put("message", this.message);
	        return "welcome";
	    }
	    @RequestMapping("/index2")
	    //@ResponseBody
	    public String index(HttpServletRequest request) {
	        HttpSession  session = request.getSession(true);
	        session.setAttribute("zxc", "zxc");
	        return  "main";
	    }

	  @RequestMapping("/online")
	    @ResponseBody
	    public Object online() {
	        return  "当前在线人数：" + MyHttpSessionListener.online + "人";
	    }
	  @RequestMapping("/")
	    public String getFir() {
	        return "main";
	    }
    @RequestMapping("/login")
    public String getMyPage() {
        return "login";
    }
    @RequestMapping("/main")
    public String getMain() {
        return "main";
    }
    @RequestMapping("/stumain")
    public String stumain() {
        return "/stu/stumain";
    }
    @RequestMapping("/teachermain")
    public String teachermain() {
        return "/teacher/teachermain";
    }
    @RequestMapping("/instrmain")
    public String instrmain() {
        return "/instructor/instrmain";
    }
    @RequestMapping("/index")
    public String getIndex() {
        return "datagrid";
    }
    @RequestMapping("/test")
    public String getLogin() {
        return "test";
    }
    @RequestMapping("/pwd")
    public String pwdEdit() {
        return "pwdEdit";
    }
    @RequestMapping("/page")
    public String page() {
        return "page";
    }
    @RequestMapping("/fil")
    public String fil() {
        return "datagrid-filter";
    }
    @RequestMapping("/per")
    public String Person() {
        return "person";
    }
    @RequestMapping("/address")
    public String Address() {
        return "address";
    }
    @RequestMapping("/stuList")
    public String stuList() {
        return "stuList";
    }
    @RequestMapping("/stu/stuNote")
    public String stuNote() {
        return "stu/stuNote";
    }
    @RequestMapping("/stu/stuNote2")
    public String stuNote2(String stuUsername ,HttpSession session) {
    	session.setAttribute("stuUsername", stuUsername);
        return "stuNote";
    }
    @RequestMapping("/instructor/instrNote")
    public String instrNote() {
        return "instructor/instrNote";
    }
    @RequestMapping("/instructor/lgclass")
    public String Instrlgclass() {
        return "/instructor/lgclass";
    }
    @RequestMapping("/teacher/situation")
    public String situation() {
        return "/teacher/situation";
    }
    @RequestMapping("/situation2")
    public String situation3(String courseId,HttpSession session) {
    	session.setAttribute("courseId3", courseId);
        return "/situation2";
    }
    @RequestMapping("/college")
    public String college() {
        return "college";
    }
    @RequestMapping("/lgclass")
    public String lgclass() {
        return "lgclass";
    }
    @RequestMapping("/lgclass/stu")
    public String lgclassStu(Long classId,HttpServletRequest request) {
    	//String classId = request.getParameter("classId");
    	LGclass lGclass = new LGclass();
    	lGclass = CenterService.findLGclassById(classId);
    	String titleClass =lGclass.getCollegeName()+"-"+lGclass.getName();
    	System.out.println("title:"+titleClass);
    	System.out.println("LGclassId:"+classId);
    	 HttpSession session = request.getSession();
    	session.setAttribute("titleClass", titleClass);
    	session.setAttribute("classId", classId);
        return "redirect:/lgclassStu";
    }
    @RequestMapping("/instructor/lgclass/stu")
    public String lgclassStu2(Long classId,HttpServletRequest request) {
    	//String classId = request.getParameter("classId");
    	LGclass lGclass = new LGclass();
    	lGclass = CenterService.findLGclassById(classId);
    	String titleClass =lGclass.getCollegeName()+"-"+lGclass.getName();
    	System.out.println("title:"+titleClass);
    	System.out.println("LGclassId:"+classId);
    	 HttpSession session = request.getSession();
    	session.setAttribute("titleClass", titleClass);
    	session.setAttribute("classId", classId);
        return "redirect:/instructor/lgclassStu";
    }
    @RequestMapping("/major")
    public String major() {
        return "major";
        
    }
    @RequestMapping("/lgclassStu")
    public String lgclassStu2() {
        return "lgclassStu";
        
    }
    @RequestMapping("/instructor/lgclassStu")
    public String lgclassStu() {
        return "/instructor/lgclassStu";
        
    }
    @RequestMapping("/TeacherList")
    public String TeacherList() {
        return "TeacherList";
        
    }
    @RequestMapping("/InstructorList")
    public String InstructorList() {
        return "InstructorList";
        
    }
    @RequestMapping("/Note")
    public String Note() {
        return "Note"; 
    }
   /* @RequestMapping("/instrNote")
    public String Note() {
        return "Note"; 
    }*/
    @RequestMapping("/course")
    public String Course() {
        return "course"; 
    }
    @RequestMapping("/courseStu")
    public String courseStu() {
        return "courseStu"; 
    }
    @RequestMapping("/situation")
    public String situation2() {
        return "situation"; 
    }
    @RequestMapping("/stuCourse")
    public String stuCourse() {
        return "/stu/stuCourse"; 
    }
    @RequestMapping("/mainTitle")
    public String mainTitle() {
        return "mainTitle"; 
    }
    @RequestMapping("/course/stu")
    public String courseStu(Long courseId,HttpServletRequest request,HttpSession session ) {
    	
    	session.setAttribute("courseId", courseId);
        return "redirect:/courseStu";
    }
    @RequestMapping("/center/drStu")
    public String drstu(Long drId,HttpServletRequest request,HttpSession session ) {
    	
    	session.setAttribute("drId", drId);
        return "drstu";
    }
  
    

}