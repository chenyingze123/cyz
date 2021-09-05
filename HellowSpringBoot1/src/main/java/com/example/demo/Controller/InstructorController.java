package com.example.demo.Controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.example.demo.entity.Instructor;
import com.example.demo.entity.LGclass;
import com.example.demo.entity.Major;
import com.example.demo.entity.Note;
import com.example.demo.entity.Student;
import com.example.demo.entity.noteType;
import com.example.demo.service.AddressService;
import com.example.demo.service.CenterService;
import com.example.demo.service.InstructorService;
import com.example.demo.service.StudentService;

import aj.org.objectweb.asm.Type;


@RestController
@RequestMapping("instructor")
public class InstructorController {
	@Autowired
    private CenterService centerService;
	@Autowired
    private InstructorService instructorService;
	@Autowired
    private StudentService studentService;
	@ResponseBody
    @RequestMapping("/NoteList") 
    public Map<String, Object> NoteList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "result", required = false) String result ,@RequestParam(value = "userLike", required = false) String userLike ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "majorId", required = false) String majorId ,@RequestParam(value = "classId", required = false) String classId ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    	System.out.println("nameLike"+nameLike);
    	System.out.println("collegeId"+collegeId);
    	 //Pageable pageable = new PageRequest(0, 10, Sort.Direction.ASC, "id");
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 System.out.println("ids:"+classId);
    	 if (nameLike == null) {
    		 nameLike = "";
		}
    	 if (userLike == null) {
    		 userLike = "";
		}
    	 if (result == null) {
    		 result = "";
		}
    	/* if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}*/
    	 if (majorId == null|| majorId.equals("")) {
    		 majorId = "0";
 		}
    	 if (classId == null|| classId.equals("")) {
    		 classId = "0";
 		}
    	 String username = (String) session.getAttribute("username");
    	 Instructor instructor = instructorService.findInstructorByUsername(username);
    	 collegeId = instructor.getCollegeId().toString();
    	
    	   Page<Note> pageUser = centerService.findByNoteLike(nameLike, userLike, collegeId, majorId, classId, result,pageable);
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
	@RequestMapping("/majorList")
    public List<Major> majorList2(HttpSession session) {
    	//System.out.println("id:"+id);
		String username = (String) session.getAttribute("username");
		Instructor instructor = instructorService.findInstructorByUsername(username);
		Long id =instructor.getCollegeId();
        return centerService.findmajorList2(id.toString());

  }
	@RequestMapping("/NoteGet")
	public String NoteGet(Long id,HttpSession session) {
		
		String username = (String) session.getAttribute("username");
    	System.out.println("id="+id);
    	Note note = new Note();
    	note = studentService.findNoteById(id);
    	if (note.getResult().equals("未审核")) {
    		/*note.setResult("<font color='red'>通过</font>");*/
    		Instructor instructor = instructorService.findInstructorByUsername(username);
    		
    		if (Integer.parseInt(note.getGrade()) <= Integer.parseInt(instructor.getGrade())) {
    			note.setResult("<font color='red'>通过</font>");
    			note.setInstrUsername(username);
        		try {
    				studentService.save(note);
    			} catch (Exception e) {
    				return "error";
    			}
			}else {
				return "error2";
			}
    		
		}
		return "success";
		

	}
	@RequestMapping("/NoteDEL")
	public String NoteDEL(Long id,HttpSession session) {
		
		String username = (String) session.getAttribute("username");
    	System.out.println("id="+id);
    	Note note = new Note();
    	note = studentService.findNoteById(id);
    	if (note.getResult().equals("未审核")) {
    		
    		Instructor instructor = instructorService.findInstructorByUsername(username);
    		if (Integer.parseInt(note.getGrade()) <= Integer.parseInt(instructor.getGrade())) {
    			note.setInstrUsername(username);
    			note.setResult("退回");
        		try {
    				studentService.save(note);
    			} catch (Exception e) {
    				return "error";
    			}
			}else {
				return "error2";
			}
    		
		}
		return "success";
		

	}
	 @ResponseBody
	    @RequestMapping("/lgclassList") 
	    public Map<String, Object> lgclassList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "majorId", required = false) String majorId ,@RequestParam(value = "page", required = false) Integer page,
		        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
	    	System.out.println("page"+page);
	    	System.out.println("rows"+rows);
	    	System.out.println("nameLike"+nameLike);
	    	System.out.println("collegeId"+collegeId);
	    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
	    	 if (nameLike == null) {
	    		 nameLike = "";
			}
	    	/* if (collegeId == null|| collegeId.equals("")) {
				collegeId = "0";
			}*/
	    	 if (majorId == null|| majorId.equals("")) {
	    		 majorId = "0";
	 		}
	    	String username = (String) session.getAttribute("username");
	    	Instructor instructor = instructorService.findInstructorByUsername(username);
	    	collegeId = instructor.getCollegeId().toString();
	    	   Page<LGclass> pageUser = centerService.findByLGclassLike(nameLike, collegeId, majorId, pageable);
		        // ①获取rows
		        List<LGclass> list = pageUser.getContent();
		        List<LGclass> list2 = new ArrayList<>();
		        for(LGclass lGclass2 :list) {
		        	lGclass2.setOpt("<a href =\"#\" ''></a>");
		        	lGclass2.setOpt("<a href=\"#\" onclick=\"look('" + lGclass2.getId()
					+ "')\"><font color=blue>学生名册</font></a>"
					+ "&nbsp;&nbsp;" + "<a href=\"#\" onclick=\"rzck('')\"><font color=blue>日志信息</font></a>");
		        	list2.add(lGclass2);
		        }
		        // ②获取count
		        Long count = centerService.countByLGclassLike(nameLike, collegeId, majorId);
		        Map<String, Object> resultMap = new HashMap<String, Object>();
		        resultMap.put("rows", list2);
		        resultMap.put("total", count);
		        resultMap.put("success", true);
		        return resultMap;
	      
	    }
	 
	    
    
  
}
