package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.example.demo.entity.College;
import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.LGclass;
import com.example.demo.entity.Major;
import com.example.demo.entity.Note;
import com.example.demo.entity.Situation;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Trainee;
import com.example.demo.service.AddressService;
import com.example.demo.service.CenterService;
import com.example.demo.service.StudentService;


@RestController
@RequestMapping("center")
public class CenterController {
	@Autowired
    private CenterService centerService;
	@Autowired
    private StudentService studentService;
    @RequestMapping("/collegeList")
    public List<College> collegeList() {
        return centerService.findCollegeList();

  }
    @RequestMapping("/collegeAdd")
    @ResponseBody
    public String collegeAdd(College college) {
    	String name = college.getName();
    	String message = college.getMessage();
    	System.out.println("name:"+name);
    	System.out.println("message:"+message);
    	List<College> list = centerService.findCollegeByName(name);
    	if (list.size() == 0) {
    		college.setIsused("Y");
        	try {
        		centerService.save(college);	
    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    		return "success";
		}else {
			
		}
		return "error1";
    	
    }

    @RequestMapping("/collegeEdit")
    @ResponseBody
    public String collegeEdit(College college) {
    	String name = college.getName();
    	String message = college.getMessage();
    	System.out.println("name:"+name);
    	System.out.println("message:"+message);
    	System.out.println("id:"+college.getId());
    	List<College> list = centerService.findCollegeByName(name);
    	if (list.size() == 0) {
    		college.setIsused("Y");
        	try {
        		centerService.edit(college);	
    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    		return "success";
		}else {
			
		}
		return "error1";
    	
    }
    @GetMapping("/collegeDel")
	public void delete(Long id) {
    	System.out.println("id="+id);
		try {
			centerService.delCollege(id);
		} catch (Exception e) {
			
		}

	}
    
    @ResponseBody
    @RequestMapping("/majorList") 
    public Map<String, Object> majorList(@RequestParam(value = "majorLike", required = false) String majorLike ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    	System.out.println("majorLike"+majorLike);
    	System.out.println("collegeId"+collegeId);
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 if (majorLike == null) {
			majorLike = "";
		}
    	 if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}
    	
    	   Page<Major> pageUser = centerService.findByMajorLike(majorLike, collegeId, pageable);
	        // ①获取rows
	        List<Major> list = pageUser.getContent();
	        Long fLong = pageUser.getTotalElements();
	        // ②获取count
	        Long count = centerService.countByMajorLike(majorLike, collegeId);
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", fLong);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    @RequestMapping("/majorAdd")
    @ResponseBody
    public String majorAdd(Major major) {
    	String name = major.getName();
    	String message = major.getMessage();
    	System.out.println("name:"+name);
    	System.out.println("message:"+message);
        major.setIsused("Y");
    	try {
    		centerService.saveMajor(major);	
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }

    @RequestMapping("/majorEdit")
    @ResponseBody
    public String majorEdit(Major major) {
    	String name = major.getName();
    	String message = major.getMessage();
    	System.out.println("name:"+name);
    	System.out.println("message:"+message);
    	System.out.println("id:"+major.getId());
    	//List<Major> list = centerService.findCollegeByName(name);
    	
    		major.setIsused("Y");
        	try {
        		centerService.editMajor(major);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    		return "success";
		
    	
    }
    @GetMapping("/majorDel")
	public void majorDel(Long id) {
    	System.out.println("id="+id);
		try {
			centerService.delMajor(id);
		} catch (Exception e) {
			
		}

	}
    
    @ResponseBody
    @RequestMapping("/lgclassList") 
    public Map<String, Object> lgclassList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "majorId", required = false) String majorId ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    	System.out.println("nameLike"+nameLike);
    	System.out.println("collegeId"+collegeId);
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 if (nameLike == null) {
    		 nameLike = "";
		}
    	 if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}
    	 if (majorId == null|| majorId.equals("")) {
    		 majorId = "0";
 		}
    	
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
    @RequestMapping("/majorList2")
    public List<Major> majorList2(@RequestParam(value = "id", required = false) String id ) {
    	System.out.println("id:"+id);
        return centerService.findmajorList2(id);

  }
    
    @RequestMapping("/classAdd")
    @ResponseBody
    public String classAdd(LGclass lGclass, HttpServletRequest request) {
    	String name = lGclass.getName();
    	String message = lGclass.getMessage();
    	String collegeId = request.getParameter("collegeId2");
    	String majorId = request.getParameter("majorId2");
    	System.out.println("name:"+name);
    	System.out.println("message:"+message);
    	System.out.println("coll:"+collegeId);
    	//System.out.println("major:"+lGclass.getMajorId());
    	lGclass.setIsused("Y");
    	lGclass.setCollegeId(Long.valueOf(collegeId));
    	lGclass.setMajorId(Long.valueOf(majorId));
    	try {
    		centerService.saveLGclass(lGclass);	
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }

    @RequestMapping("/classEdit")
    @ResponseBody
    public String classEdit(LGclass lGclass, HttpServletRequest request) {
    	String name = lGclass.getName();
    	String message = lGclass.getMessage();
    	System.out.println("name:"+name);
    	System.out.println("message:"+message);
    	System.out.println("id:"+lGclass.getId());
    	//List<Major> list = centerService.findCollegeByName(name);
    	String collegeId = request.getParameter("collegeId3");
    	String majorId = request.getParameter("majorId3");
    	lGclass.setCollegeId(Long.valueOf(collegeId));
    	lGclass.setMajorId(Long.valueOf(majorId));
    	lGclass.setIsused("Y");
        	try {
        		centerService.editLGclass(lGclass);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    		return "success";
		
    	
    }
    @GetMapping("/classDel")
	public void classDel(Long id) {
    	System.out.println("id="+id);
		try {
			centerService.delLGclass(id);
		} catch (Exception e) {
			
		}

	}
    
    @ResponseBody
    @RequestMapping("/StudentList") 
    public Map<String, Object> StudentList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "majorId", required = false) String majorId ,@RequestParam(value = "classId", required = false) String classId ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    	System.out.println("nameLike"+nameLike);
    	System.out.println("collegeId"+collegeId);
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 /*HttpSession session = ((HttpServletRequest) request).getSession(false);
    	 String id = (String) session.getAttribute("classId");
    	 System.out.println("ids:"+id);*/
    	 Object id =  session.getAttribute("classId");
    	 String classId2 = id.toString();
    	 System.out.println("ids:"+classId2);
    	 if (nameLike == null) {
    		 nameLike = "";
		}
    	 if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}
    	 if (majorId == null|| majorId.equals("")) {
    		 majorId = "0";
 		}
    	 if (classId == null|| classId.equals("")) {
    		 classId = "0";
 		}
    	 if (classId2 != null &&  !classId2.equals("")) {
    		 classId = classId2;
 		}
    	 
    	
    	   Page<Student> pageUser = centerService.findByStudentLike(nameLike, collegeId, majorId, classId, pageable);
	        // ①获取rows
	        List<Student> list = pageUser.getContent();
	        // ②获取count
	        Long count = centerService.countByStudentLike(nameLike, collegeId, majorId, classId);
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    @ResponseBody
    @RequestMapping("/StudentListAll") 
    public Map<String, Object> StudentListAll(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "userLike", required = false) String userLike ,@RequestParam(value = "collegeId2", required = false) String collegeId ,@RequestParam(value = "majorId2", required = false) String majorId ,@RequestParam(value = "classId2", required = false) String classId ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    	System.out.println("nameLike"+nameLike);
    	System.out.println("collegeId"+collegeId);
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 System.out.println("ids:"+classId);
    	 if (nameLike == null) {
    		 nameLike = "";
		}
    	 if (userLike == null) {
    		 userLike = "";
		}
    	 if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}
    	 if (majorId == null|| majorId.equals("")) {
    		 majorId = "0";
 		}
    	 if (classId == null|| classId.equals("")) {
    		 classId = "0";
 		}
    	 
    	
    	   Page<Student> pageUser = centerService.findByStudentLike2(nameLike, userLike, collegeId, majorId, classId, pageable);
	        // ①获取rows
	        List<Student> list = pageUser.getContent();
	        // ②获取count
	        Long count = centerService.countByStudentLike2(nameLike,userLike, collegeId, majorId,  classId, pageable);
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    @RequestMapping("/stuAdd")
    @ResponseBody
    public String stuAdd(Student student, HttpServletRequest request,HttpSession session) {
    	LGclass lGclass = new LGclass();
    	Object classId = session.getAttribute("LGclassId");
    	Long classId2 = Long.valueOf(String.valueOf(classId));
    	lGclass = centerService.findLGclassById(classId2);
    	
    	//System.out.println("major:"+lGclass.getMajorId());
    	student.setMajorId(lGclass.getMajorId());
    	student.setCollegeId(lGclass.getCollegeId());
    	student.setClassId(classId2);
    	student.setPassword("123456");
    	String sexName = student.getSex();
    	if (sexName.equals("男")) {
			student.setSex("1");
		}else {
			student.setSex("0");
		}
    	try {
    		centerService.saveStudent(student);	
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }

    @RequestMapping("/stuEdit")
    @ResponseBody
    public String stuEdit(Student student, HttpServletRequest request) {

    	String sexName = request.getParameter("sex");
    	if (sexName.equals("男")) {
			student.setSex("1");
		}else {
			student.setSex("0");
		}
        	try {
        		centerService.editStudent(student);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    		return "success";
		
    	
    }
    
    @RequestMapping("/stuAdd2")
    @ResponseBody
    public String stuAdd2(Student student, HttpServletRequest request,HttpSession session) {
    	String classId = request.getParameter("classId3");
    	String majorId = request.getParameter("majorId3");
    	String collegeId = request.getParameter("collegeId3");
    	System.out.println("collegeId3:"+collegeId);
    	//System.out.println("major:"+lGclass.getMajorId());
    	student.setMajorId(Long.valueOf(String.valueOf(majorId)));
    	student.setCollegeId(Long.valueOf(String.valueOf(collegeId)));
    	student.setClassId(Long.valueOf(String.valueOf(classId)));
    	student.setPassword("123456");
    	String sexName = student.getSex();
    	if (sexName.equals("男")) {
			student.setSex("1");
		}else {
			student.setSex("0");
		}
    	try {
    		centerService.saveStudent(student);	
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }

    @RequestMapping("/stuEdit2")
    @ResponseBody
    public String stuEdit2(Student student, HttpServletRequest request) {

    	String sexName = request.getParameter("sex");
    	if (sexName.equals("男")) {
			student.setSex("1");
		}else {
			student.setSex("0");
		}
        	try {
        		centerService.editStudent(student);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return "error";
    		}
    		return "success";
		
    	
    }
    @GetMapping("/stuDel")
	public void stuDel(Long id) {
    	System.out.println("id="+id);
		try {
			centerService.delStudent(id);
		} catch (Exception e) {
			
		}

	}
    @RequestMapping("/classList2")
    public List<LGclass> classList2(@RequestParam(value = "id", required = false) String id ) {
    	System.out.println("id:"+id);
    	System.out.println();
        return centerService.findLGclassList2(id);
     

  }
    @ResponseBody
    @RequestMapping("/TeacherList") 
    public Map<String, Object> TeacherList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "collegeId2", required = false) String collegeId ,@RequestParam(value = "userLike", required = false) String userLike ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    	System.out.println("nameLike"+nameLike);
    	System.out.println("collegeId"+collegeId);
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	// System.out.println("ids:"+classId);
    	 if (nameLike == null) {
    		 nameLike = "";
		}
    	 if (userLike == null) {
    		 userLike = "";
		}
    	 if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}


    	 
    	
    	   Page<Teacher> pageUser = centerService.findByTeacherLike(nameLike, userLike, collegeId,  pageable);
	        // ①获取rows
	        List<Teacher> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageUser.getTotalElements();
	        //Long count = centerService.countByStudentLike2(nameLike,userLike, collegeId, majorId,  classId, pageable);
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    
    @ResponseBody
    @RequestMapping("/InstructorList") 
    public Map<String, Object> InstructorList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "collegeId2", required = false) String collegeId ,@RequestParam(value = "userLike", required = false) String userLike ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	System.out.println("page"+page);
    	System.out.println("rows"+rows);
    	System.out.println("nameLike"+nameLike);
    	System.out.println("collegeId"+collegeId);
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	// System.out.println("ids:"+classId);
    	 if (nameLike == null) {
    		 nameLike = "";
		}
    	 if (userLike == null) {
    		 userLike = "";
		}
    	 if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}


    	 
    	
    	   Page<Instructor> pageUser = centerService.findByInstructorLike(nameLike, userLike, collegeId,  pageable);
	        // ①获取rows
	        List<Instructor> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageUser.getTotalElements();
	        //Long count = centerService.countByStudentLike2(nameLike,userLike, collegeId, majorId,  classId, pageable);
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    @RequestMapping("/teacherAdd")
    @ResponseBody
    public String teacherAdd(Teacher teacher, HttpServletRequest request,HttpSession session) {
    	/*String classId = request.getParameter("classId3");
    	String majorId = request.getParameter("majorId3");*/
    	String collegeId = request.getParameter("collegeId3");
    	System.out.println("collegeId3:"+collegeId);
    	//System.out.println("major:"+lGclass.getMajorId());
    	//teacher.setMajorId(Long.valueOf(String.valueOf(majorId)));
    	teacher.setCollegeId(Long.valueOf(String.valueOf(collegeId)));
    	//teacher.setClassId(Long.valueOf(String.valueOf(classId)));
    	teacher.setPassword("123456");
    	String sexName = teacher.getSex();
    	if (sexName.equals("男")) {
    		teacher.setSex("1");
		}else {
			teacher.setSex("0");
		}
    	try {
    		centerService.saveTeacher(teacher);	
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }
    @RequestMapping("/teacherEdit")
    @ResponseBody
    public String teacherEdit(Teacher teacher, HttpServletRequest request,HttpSession session) {
    	/*String classId = request.getParameter("classId3");
    	String majorId = request.getParameter("majorId3");*/
    	String collegeId = request.getParameter("collegeId3");
    	System.out.println("collegeId3:"+collegeId);
    	//System.out.println("major:"+lGclass.getMajorId());
    	//teacher.setMajorId(Long.valueOf(String.valueOf(majorId)));
    	//teacher.setCollegeId(Long.valueOf(String.valueOf(collegeId)));
    	//teacher.setClassId(Long.valueOf(String.valueOf(classId)));
    	teacher.setPassword("123456");
    	String sexName = teacher.getSex();
    	if (sexName.equals("男")) {
    		teacher.setSex("1");
		}else {
			teacher.setSex("0");
		}
    	try {
    		centerService.saveTeacher(teacher);	
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }

    @GetMapping("/teacherDel")
	public void teacherDel(Long id) {
    	System.out.println("id="+id);
		try {
			centerService.delTeacher(id);
		} catch (Exception e) {
			
		}

	}
    @RequestMapping("/instrAdd")
    @ResponseBody
    public String instrAdd(Instructor instructor, HttpServletRequest request,HttpSession session) {
    	/*String classId = request.getParameter("classId3");
    	String majorId = request.getParameter("majorId3");*/
    	String collegeId = request.getParameter("collegeId3");
    	System.out.println("collegeId3:"+collegeId);
    	//System.out.println("major:"+lGclass.getMajorId());
    	//teacher.setMajorId(Long.valueOf(String.valueOf(majorId)));
    	instructor.setCollegeId(Long.valueOf(String.valueOf(collegeId)));
    	//teacher.setClassId(Long.valueOf(String.valueOf(classId)));
    	instructor.setPassword("123456");
    	String sexName = instructor.getSex();
    	if (sexName.equals("男")) {
    		instructor.setSex("1");
		}else {
			instructor.setSex("0");
		}
    	try {
    		centerService.saveInstructor(instructor);	
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }
    @RequestMapping("/instrEdit")
    @ResponseBody
    public String instrEdit(Instructor instructor, HttpServletRequest request,HttpSession session) {
    	/*String classId = request.getParameter("classId3");
    	String majorId = request.getParameter("majorId3");*/
    	String collegeId = request.getParameter("collegeId3");
    	System.out.println("collegeId3:"+collegeId);
    	//System.out.println("major:"+lGclass.getMajorId());
    	//teacher.setMajorId(Long.valueOf(String.valueOf(majorId)));
    	//teacher.setCollegeId(Long.valueOf(String.valueOf(collegeId)));
    	//teacher.setClassId(Long.valueOf(String.valueOf(classId)));
    	instructor.setPassword("123456");
    	String sexName = instructor.getSex();
    	if (sexName.equals("男")) {
    		instructor.setSex("1");
		}else {
			instructor.setSex("0");
		}
    	try {
    		centerService.saveInstructor(instructor);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
    	
    }

    @GetMapping("/instrDel")
	public void instrDel(Long id) {
    	System.out.println("id="+id);
		try {
			centerService.delInstructor(id);
		} catch (Exception e) {
			
		}

	}
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
    	 if (collegeId == null|| collegeId.equals("")) {
			collegeId = "0";
		}
    	 if (majorId == null|| majorId.equals("")) {
    		 majorId = "0";
 		}
    	 if (classId == null|| classId.equals("")) {
    		 classId = "0";
 		}
    	 
    	
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
    @GetMapping("/NoteDel")
   	public void NoteDel(Long id) {
       	System.out.println("id="+id);
   		try {
   			centerService.delInstructor(id);
   		} catch (Exception e) {
   			
   		}

   	}
    @RequestMapping("/teacherList2")
    public List<Teacher> findTeacherByCollegeId(String id) {
		return centerService.findTeacherByCollegeId(id);
    	
    }
    

    @ResponseBody
    @RequestMapping("/courseList") 
    public Map<String, Object> courseList(@RequestParam(value = "nameLike", required = false) String nameLike  ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "teacherId", required = false) String teacherId ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	if (nameLike == null) {
			nameLike = "";
		}
    	if (collegeId == null || collegeId.equals("")) {
			collegeId = "0";
		}
    	if (teacherId == null || teacherId.equals("")) {
    		teacherId = "0";
		}
    	String usertea = (String) session.getAttribute("userTea");
    	
    	if (usertea!= null && !usertea.equals("")) {
    		Teacher teacher = centerService.findTeacherByUsername(usertea);
    		teacherId = teacher.getId().toString();
		}
    	System.out.println("teacher:"+teacherId);
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 Page<Course> pageUser = centerService.findByCourseLike(nameLike, collegeId, teacherId, pageable);
	        // ①获取rows
	        List<Course> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageUser.getTotalElements();
	        List<Course> list2 = new ArrayList<>();
	        for(Course course :list) {
	        	course.setOpt("<a href =\"#\" ''></a>");
	        	course.setOpt("<a href=\"#\" onclick=\"look('" + course.getId()
				+ "')\"><font color=blue>学生名册</font></a>"
				+ "&nbsp;&nbsp;" + "<a href=\"#\" onclick=\"rzck('"+course.getId()+"')\"><font color=blue>日志信息</font></a>");
	        	list2.add(course);
	        }
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list2);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;

    }
    @ResponseBody
    @RequestMapping("/CourseStuList") 
    public Map<String, Object> CourseStuList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	 Long courseId = (Long) session.getAttribute("courseId");
    	 //String
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 /*HttpSession session = ((HttpServletRequest) request).getSession(false);
    	 String id = (String) session.getAttribute("classId");
    	 System.out.println("ids:"+id);*/
    	 /*Object id =  session.getAttribute("classId");
    	 String classId2 = id.toString();
    	 System.out.println("ids:"+classId2);*/
    	 if (nameLike == null) {
    		 nameLike = "";
		}
    	 
    	 
    	
    	   Page<Trainee> pageUser = centerService.findByTraineeLike(courseId, nameLike, pageable);
	        // ①获取rows
	        List<Trainee> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageUser.getTotalElements();
	        List<Trainee> list2 = new ArrayList<>();
	        for(Trainee trainee :list) {
	        	//trainee.setOpt("<a href =\"#\" ''></a>");
	        	trainee.setOpt("<a href=\"#\" onclick=\"look('" + trainee.getStuUsername()
				+ "')\"><font color=blue>请假信息</font></a>"
				+ "&nbsp;&nbsp;" + "<a href=\"#\" onclick=\"rzck('')\"><font color=blue>日志信息</font></a>");
	        	list2.add(trainee);
	        }
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list2);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    
    @ResponseBody
    @RequestMapping("/NoteStuList") 
    public Map<String, Object> NoteStuList(@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	// Long courseId = (Long) session.getAttribute("courseId");
    	 //String
    	 String stuUsername =(String) session.getAttribute("stuUsername");
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 /*HttpSession session = ((HttpServletRequest) request).getSession(false);
    	 String id = (String) session.getAttribute("classId");
    	 System.out.println("ids:"+id);*/
    	 /*Object id =  session.getAttribute("classId");
    	 String classId2 = id.toString();
    	 System.out.println("ids:"+classId2);*/
    	 /*if (StuUsername == null) {
    		 StuUsername = "";
		}*/
    	   Page<Note> pageUser = studentService.findNoteByUsername2(stuUsername, pageable);
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
    @ResponseBody
    @RequestMapping("/situationList") 
    public Map<String, Object> situationList(@RequestParam(value = "courseId", required = false) String courseId,@RequestParam(value = "nameLike2", required = false) String nameLike2,@RequestParam(value = "nameLike", required = false) String nameLike,@RequestParam(value = "teacherId", required = false) String teacherId,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	// Long courseId = (Long) session.getAttribute("courseId");
    	 //String
    	 String stuUsername =(String) session.getAttribute("stuUsername");
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 /*HttpSession session = ((HttpServletRequest) request).getSession(false);
    	 String id = (String) session.getAttribute("classId");
    	 System.out.println("ids:"+id);*/
    	 /*Object id =  session.getAttribute("classId");
    	 String classId2 = id.toString();
    	 System.out.println("ids:"+classId2);*/
    	 /*if (StuUsername == null) {
    		 StuUsername = "";
		}*/if (nameLike ==null) {
			nameLike ="";
		}
		if (nameLike2 != null && !nameLike2.equals("")) {
			nameLike=nameLike2;
		}
		if (teacherId == null || teacherId.equals("")) {
			teacherId = "0";
		}
        String usertea = (String) session.getAttribute("userTea");
    	if (usertea!= null && !usertea.equals("")) {
    		Teacher teacher = centerService.findTeacherByUsername(usertea);
    		teacherId = teacher.getId().toString();
		}
		if (courseId == null || courseId.equals("")) {
			courseId = "0";
		}
    	   Page<Situation> pageUser = centerService.findBySituationLike(nameLike, teacherId, courseId, pageable);
	        // ①获取rows
	        List<Situation> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageUser.getTotalElements();
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    
    @ResponseBody
    @RequestMapping("/situationList2") 
    public Map<String, Object> situationList2(@RequestParam(value = "courseId", required = false) String courseId,@RequestParam(value = "nameLike", required = false) String nameLike,@RequestParam(value = "teacherId", required = false) String teacherId,@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "rows", required = false) Integer rows,HttpSession session) {
    	// Long courseId = (Long) session.getAttribute("courseId");
    	 //String
    	 String stuUsername =(String) session.getAttribute("stuUsername");
    	 Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.ASC, "id");
    	 /*HttpSession session = ((HttpServletRequest) request).getSession(false);
    	 String id = (String) session.getAttribute("classId");
    	 System.out.println("ids:"+id);*/
    	 /*Object id =  session.getAttribute("classId");
    	 String classId2 = id.toString();
    	 System.out.println("ids:"+classId2);*/
    	 /*if (StuUsername == null) {
    		 StuUsername = "";
		}*/if (nameLike ==null) {
			nameLike ="";
		}
		if (teacherId == null || teacherId.equals("")) {
			teacherId = "0";
		}
		if (courseId == null || courseId.equals("")) {
			courseId = "0";
		}
		String courseId2 =(String) session.getAttribute("courseId3");
    	   Page<Situation> pageUser = centerService.findBySituationLike(nameLike, teacherId, courseId2, pageable);
	        // ①获取rows
	        List<Situation> list = pageUser.getContent();
	        // ②获取count
	        Long count = pageUser.getTotalElements();
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("rows", list);
	        resultMap.put("total", count);
	        resultMap.put("success", true);
	        return resultMap;
      
    }
    @RequestMapping("/courseAdd")
    @ResponseBody
    public String courseAdd(Course course,Long teacherId3,Long collegeId3) {
    	course.setTeacherId(teacherId3);
    	course.setCollegeId(collegeId3);
    	//List<College> list = centerService.findCollegeByName(name);
    	try {
    		centerService.saveCourse(course);
		} catch (Exception e) {
			return "error";
		}
		return "success";
    	
    }
    @RequestMapping("/courseEdit")
    @ResponseBody
    public String courseEdit(Course course) {
    	/*course.setTeacherId(teacherId3);
    	course.setCollegeId(collegeId3);*/
    	//List<College> list = centerService.findCollegeByName(name);
    	try {
    		centerService.saveCourse(course);
		} catch (Exception e) {
			return "error";
		}
		return "success";
    	
    }
    @GetMapping("/courseDel")
	public void courseDel(Long id) {
    	System.out.println("id="+id);
		try {
			centerService.delCourseById(id);
		} catch (Exception e) {
			
		}

	}
    @RequestMapping("/traineeAdd")
    @ResponseBody
    public String traineeAdd(Trainee trainee,HttpSession session) {
    	Long id = (Long) session.getAttribute("courseId");
    	trainee.setId(id);
    	trainee.setCourseId(id);
    	//List<College> list = centerService.findCollegeByName(name);
    	//Student student = studentService.findByUsername(username);
    	try {
    		centerService.saveTrainee(id, trainee.getStuUsername());
		} catch (Exception e) {
			return "error";
		}
		return "success";
    	
    }
    @RequestMapping("/traineeEdit")
    @ResponseBody
    public String traineeEdit(Trainee trainee,HttpSession session) {
    	Long id = (Long) session.getAttribute("courseId");
    	trainee.setId(id);
    	trainee.setCourseId(id);
    	//List<College> list = centerService.findCollegeByName(name);
    	try {
    		centerService.saveTrainee(trainee);
		} catch (Exception e) {
			return "error";
		}
		return "success";
    	
    }
    @GetMapping("/traineeDel")
	public void traineeDel(Long id,String stuUsername) {
    	System.out.println("id="+id);
		try {
			centerService.delTraineeByIdAndStu(id, stuUsername);
		} catch (Exception e) {
			
		}

	}
    @RequestMapping("/situationAdd")
    @ResponseBody
    public String situationAdd(Situation situation,HttpSession session) {
    	String id = (String) session.getAttribute("courseId3");
    	String username = (String) session.getAttribute("userTea");
    	Teacher teacher = centerService.findTeacherByUsername(username);
    	
    	if (teacher!=null) {
			situation.setTeacherId(teacher.getId());
			situation.setCourseId(Long.parseLong(id));
			try {
				centerService.saveSituation(situation);
			} catch (Exception e) {
				// TODO: handle exception
				return "error";
			}
		}else {
			return "error";
		}
    	
    	//List<College> list = centerService.findCollegeByName(name);
    	//Student student = studentService.findByUsername(username);
    	/*try {
    		centerService.saveTrainee(id, trainee.getStuUsername());
		} catch (Exception e) {
			return "error";
		}*/
		return "success";
    	
    }
    
  
}
