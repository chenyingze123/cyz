package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.CollegeRepository;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.InstructorRepository;
import com.example.demo.Repository.LGclassRepository;
import com.example.demo.Repository.MajorRepository;
import com.example.demo.Repository.NoteRepository;
import com.example.demo.Repository.SituationRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.TeacherRepository;
import com.example.demo.Repository.TraineeRepository;
import com.example.demo.entity.Address;
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
@Service
public class CenterServiceImpl  implements CenterService{
	@Autowired
	private CollegeRepository collegedao;
	@Autowired
	private MajorRepository  majordao;
	@Autowired
	private LGclassRepository lGclassdao;
	@Autowired
	private StudentRepository studentdao;
	@Autowired
	private TeacherRepository teacherdao;
	@Autowired
	private InstructorRepository instructordao;
	@Autowired
	private NoteRepository notedao;
	@Autowired
	private CourseRepository coursedao;
	@Autowired
	private TraineeRepository traineedao;
	@Autowired
	private SituationRepository situationdao;

	@Override
	public List<College> findCollegeList() {
		// TODO Auto-generated method stub
		return collegedao.findCollegeList();
	}

	@Override
	public List<College> findCollegeByName(String name) {
		// TODO Auto-generated method stub
		return collegedao.findCollegeByName(name);
	}

	@Override
	public College save(College college) {
		// TODO Auto-generated method stub
		return collegedao.save(college);
	}

	@Override
	public College edit(College college) {
		// TODO Auto-generated method stub
		return collegedao.save(college);
	}

	@Override
	public void delCollege(Long id) {
		collegedao.delCollegeById(id);
		
	}

	@Override
	public Page<Major> findByMajorLike(String nameLike, String collegeId, Pageable pageable) {
	  return majordao.findByMajorLike(nameLike, collegeId, pageable);
	}

	@Override
	public long countByMajorLike(String nameLike, String collegeId) {
		// TODO Auto-generated method stub
		return majordao.countByMajorLike(nameLike, collegeId);
	}

	@Override
	public Major saveMajor(Major major) {
		// TODO Auto-generated method stub
		return majordao.save(major);
	}

	@Override
	public Major editMajor(Major major) {
		// TODO Auto-generated method stub
		return majordao.save(major);
	}

	@Override
	public void delMajor(Long id) {
		// TODO Auto-generated method stub
		majordao.delMajorById(id);
	}

	@Override
	public long countByLGclassLike(String nameLike, String collegeId, String majorId) {
		// TODO Auto-generated method stub
		return lGclassdao.countByLGclassLike(nameLike, collegeId, majorId);
	}

	@Override
	public Page<LGclass> findByLGclassLike(String nameLike, String collegeId, String majorId, Pageable pageable) {
		// TODO Auto-generated method stub
		return lGclassdao.findByLGclassLike(nameLike, collegeId, majorId, pageable);
	}

	@Override
	public List<Major> findmajorList2(String id) {
		// TODO Auto-generated method stub
		return majordao.findmajorList2(id);
	}

	@Override
	public LGclass saveLGclass(LGclass lGclass) {
		// TODO Auto-generated method stub
		return lGclassdao.save(lGclass);
	}

	@Override
	public LGclass editLGclass(LGclass lGclass) {
		// TODO Auto-generated method stub
		return lGclassdao.save(lGclass);
	}

	@Override
	public void delLGclass(Long id) {
		lGclassdao.delLGclassById(id);
		
	}

	@Override
	public LGclass findLGclassById(Long id) {
		// TODO Auto-generated method stub
		return lGclassdao.findLGclassById(id);
	}

	@Override
	public long countByStudentLike(String nameLike, String collegeId, String majorId, String classId) {
		// TODO Auto-generated method stub
		return studentdao.countByStudentLike(nameLike, collegeId, majorId, classId);
	}

	@Override
	public Page<Student> findByStudentLike(String nameLike, String collegeId, String majorId, String classId,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return studentdao.findByStudentLike(nameLike, collegeId, majorId, classId, pageable);
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentdao.save(student);
	}

	@Override
	public Student editStudent(Student student) {
		// TODO Auto-generated method stub
		return studentdao.save(student);
	}

	@Override
	public void delStudent(Long id) {
		studentdao.deleteById(id);
		
	}

	@Override
	public List<LGclass> findLGclassList2(String id) {
		// TODO Auto-generated method stub
		return lGclassdao.findLGclassList2(id);
	}

	@Override
	public Page<Student> findByStudentLike2(String nameLike, String userLike, String collegeId, String majorId,
			String classId, Pageable pageable) {
		// TODO Auto-generated method stub
		return studentdao.findByStudentLike2(nameLike, userLike, collegeId, majorId, classId, pageable);
	}

	@Override
	public long countByStudentLike2(String nameLike, String userLike, String collegeId, String majorId, String classId,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return studentdao.countByStudentLike2(nameLike, userLike, collegeId, majorId, classId);
	}

	@Override
	public Page<Teacher> findByTeacherLike(String nameLike,String userLike, String collegeId, Pageable pageable) {
		// TODO Auto-generated method stub
		return teacherdao.findByTeacherLike(nameLike,  userLike,collegeId, pageable);
	}

	@Override
	public Page<Instructor> findByInstructorLike(String nameLike,String userLike, String collegeId, Pageable pageable) {
		// TODO Auto-generated method stub
		return instructordao.findByInstructorLike(nameLike, userLike, collegeId, pageable);
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherdao.save(teacher);
	}

	@Override
	public void delTeacher(Long id) {
		teacherdao.deleteById(id);
		
	}

	@Override
	public Instructor saveInstructor(Instructor instructor) {
		// TODO Auto-generated method stub
		return instructordao.save(instructor);
	}

	@Override
	public void delInstructor(Long id) {
	instructordao.deleteById(id);
		
	}

	@Override
	public Page<Note> findByNoteLike(String nameLike, String userLike, String collegeId, String majorId, String classId,String result,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return notedao.findByNoteLike(nameLike, userLike, collegeId, majorId, classId, result,pageable);
	}

	@Override
	public void delNote(Long id) {
		notedao.deleteById(id);
		
	}

	@Override
	public Page<Course> findByCourseLike(String nameLike, String collegeId, String teacherId, Pageable pageable) {
		// TODO Auto-generated method stub
		return coursedao.findByCourseLike(nameLike, collegeId, teacherId, pageable);
	}

	@Override
	public List<Teacher> findTeacherByCollegeId(String id) {
		// TODO Auto-generated method stub
		return teacherdao.findTeacherByCollegeId(id);
	}

	@Override
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return coursedao.save(course);
	}

	@Override
	public Page<Trainee> findByTraineeLike(Long id, String username, Pageable pageable) {
		// TODO Auto-generated method stub
		return traineedao.findByTraineeLike(id, username, pageable);
	}

	@Override
	public Page<Situation> findBySituationLike(String nameLike, String teacherId, String courseId, Pageable pageable) {
		// TODO Auto-generated method stub
		return situationdao.findBySituationLike(nameLike, teacherId, courseId, pageable);
	}

	@Override
	public Teacher findTeacherByUsername(String username) {
		// TODO Auto-generated method stub
		return teacherdao.findTeacherByUsername(username);
	}

	@Override
	public void delNoteById(Long id) {
		notedao.deleteById(id);
		
	}

	@Override
	public void delSituationById(Long id) {
		situationdao.deleteById(id);
		
	}

	@Override
	public Situation saveSituation(Situation situation) {
		// TODO Auto-generated method stub
		return situationdao.save(situation);
	}

	@Override
	public void delCourseById(Long id) {
		// TODO Auto-generated method stub
		coursedao.deleteById(id);
		traineedao.deleteById(id);
	}

	@Override
	public Trainee saveTrainee(Trainee trainee) {
		// TODO Auto-generated method stub
		return traineedao.save(trainee);
	}

	@Override
	public void delTraineeByIdAndStu(Long id, String username) {
		traineedao.delTraineeByIdAndStu(id, username);
	}

	@Override
	public void  saveTrainee(Long id, String username) {
		// TODO Auto-generated method stub
		 traineedao.saveTrainee(id, username);
	}
	
	

}
