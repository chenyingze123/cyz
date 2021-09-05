package com.example.demo.Controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.bouncycastle.jce.provider.JDKDSASigner.noneDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Course;
import com.example.demo.entity.Major;
import com.example.demo.entity.Note;
import com.example.demo.entity.Student;
import com.example.demo.entity.noteType;
import com.example.demo.service.AddressService;
import com.example.demo.service.CenterService;
import com.example.demo.service.StudentService;

import aj.org.objectweb.asm.Type;


@RestController
@RequestMapping("student")
public class StudentController {
	@Autowired
    private CenterService centerService;
	@Autowired
    private StudentService studentService;
	 @ResponseBody
	    @RequestMapping("/NoteList") 
	    public Map<String, Object> NoteList(@RequestParam(value = "page", required = false) Integer page,
		        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
	    	System.out.println("page"+page);
	    	System.out.println("rows"+rows);
	    
	    	 //Pageable pageable = new PageRequest(0, 10, Sort.Direction.ASC, "id");
	    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
	    	 String username = (String) session.getAttribute("username");
	    	 System.out.println("username:"+username);
	    	 
	 
	    	   Page<Note> pageUser = studentService.findNoteByUsername(username, pageable);
		        // ①获取rows
		        List<Note> list = pageUser.getContent();
		        // ②获取count
		        Long count = pageUser.getTotalElements();
		        Map<String, Object> resultMap = new HashMap<String, Object>();
		        resultMap.put("rows", list);
		        resultMap.put("total", count);
		        resultMap.put("success", true);
		        return resultMap;
	      
	    }
	 
	 //@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/NoteDel")
		public void delete(Long id) {
	    	System.out.println("id="+id);
	    	Note note = new Note();
	    	note = studentService.findNoteById(id);
	    	if (note.getResult().equals("未审核")) {
	    		note.setResult("撤回");
	    		try {
					studentService.save(note);
				} catch (Exception e) {
					
				}
			}
			

		}
	 @RequestMapping("/NoteAdd")
	    @ResponseBody
	    public String NoteAdd(Note note,HttpSession session) {
	    	/*String name = major.getName();
	    	String message = major.getMessage();
	    	System.out.println("name:"+name);
	    	System.out.println("message:"+message);
	        major.setIsused("Y");*/
		   //String time = note.getEndtime().
		    Date date = new Date();
		    Date date2 = new Date();
		    SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    try {
				date = sdf.parse(note.getStarttime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				date2 = sdf.parse(note.getEndtime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    long time = date2.getTime() - date.getTime();
		    time = time /(1000*60*60*24);
		    System.out.println("time :" +time);
		    if (time <= 0) {
				return "error2";
			}else {
				if (time >= 1 && time <=3) {
					note.setGrade("1");
				}
				if (time >= 4 && time <=6) {
					note.setGrade("2");
				}
				if (time >= 7 ) {
					note.setGrade("3");
				}
				String username =(String) session.getAttribute("username");
				   Student student = studentService.findByUsername(username);
				   note.setClassId(student.getClassId());
				   note.setCollegeId(student.getCollegeId());
				   note.setMajorId(student.getMajorId());
				   note.setStuUsername(student.getUsername());
				   note.setResult("未审核");
			    	try {
			    		//centerService.saveMajor(major);
			    		studentService.save(note);
					} catch (Exception e) {
						e.printStackTrace();
						return "error";
					}
					return "success";
			}
		    
		   
	    	
	    }
	@RequestMapping("/typeList")
	public List<noteType> typeList(){
		
		return studentService.findAlltype();
		
	}
	@ResponseBody
    @RequestMapping("/courseList") 
    public Map<String, Object> courseList(@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    
    	 //Pageable pageable = new PageRequest(0, 10, Sort.Direction.ASC, "id");
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 /*String username = (String) session.getAttribute("username");
    	 System.out.println("username:"+username);*/
    	String userStu =  (String) session.getAttribute("userStu");
 
    	   Page<Course> pageUser = studentService.findByCourseLike2(userStu, pageable);
	        // ①获取rows
	        List<Course> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageUser.getTotalElements();
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    
  
}
