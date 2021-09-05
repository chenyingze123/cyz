package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.parser.Part.IgnoreCaseType;

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



public interface CenterService {
	/*List<Address> findAll();
	Address save(Address address);
	Address findByaddressName(String addressName);
	Address edit(Address address);
	void deleteById(Long addressId);
	Address findByaddressId(Long addressId);*/
	List<College> findCollegeList();
	List<College> findCollegeByName(String name);
	College save(College college);
	College edit(College college);
	void delCollege(Long id);
	Page<Major> findByMajorLike(String nameLike,String collegeId,Pageable pageable);
	long countByMajorLike(String nameLike,String collegeId);
	Major saveMajor(Major major);
	Major editMajor(Major major);
	void delMajor(Long id);
	long countByLGclassLike(String nameLike,String collegeId,String majorId);
	Page<LGclass> findByLGclassLike(String nameLike, String collegeId,String majorId, Pageable pageable);
	List<Major> findmajorList2(String id);
    LGclass saveLGclass(LGclass lGclass);
    LGclass editLGclass(LGclass lGclass);
	void delLGclass(Long id);
	LGclass findLGclassById(Long id);
	long countByStudentLike(String nameLike,String collegeId,String majorId,String classId);
	Page<Student> findByStudentLike(String nameLike, String collegeId,String majorId,String classId, Pageable pageable);
	 Student saveStudent(Student student);
	 Student editStudent(Student student);
     void delStudent(Long id);
     List<LGclass> findLGclassList2(String id);
     Page<Student> findByStudentLike2(String nameLike,String userLike, String collegeId,String majorId,String classId, Pageable pageable);
     long countByStudentLike2(String nameLike,String userLike, String collegeId,String majorId,String classId, Pageable pageable);
     Page<Teacher> findByTeacherLike(String nameLike,String userLike, String collegeId, Pageable pageable);
     Page<Instructor> findByInstructorLike(String nameLike, String userLike,String collegeId, Pageable pageable);
     Teacher saveTeacher(Teacher teacher);
     void delTeacher(Long id);
     Instructor saveInstructor(Instructor instructor);
     void delInstructor(Long id);
     Page<Note> findByNoteLike(String nameLike,String userLike, String collegeId,String majorId,String classId, String result,Pageable pageable);
     void delNote(Long id);
     Page<Course> findByCourseLike(String nameLike,String collegeId,  String teacherId, Pageable pageable);
     List<Teacher> findTeacherByCollegeId(String id);
     Course saveCourse(Course course);
     Page<Trainee> findByTraineeLike(Long id,String username, Pageable pageable);
     Page<Situation> findBySituationLike(String nameLike,String teacherId, String courseId, Pageable pageable);
     Teacher findTeacherByUsername(String username);
     void delNoteById(Long id);
     void delSituationById(Long id);
     Situation saveSituation(Situation situation);
     void delCourseById(Long id);
     Trainee saveTrainee(Trainee trainee);
     void delTraineeByIdAndStu(Long id,String username);
     void saveTrainee(Long id, String username); 
     //Course saveCourse(Course course);
}
