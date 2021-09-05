package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.NoteRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.TypeRepository;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Course;
import com.example.demo.entity.Note;
import com.example.demo.entity.Student;
import com.example.demo.entity.noteType;
import com.example.demo.service.AdminService;
import com.example.demo.service.StudentService;

import aj.org.objectweb.asm.Type;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentDao;
	@Autowired
	private NoteRepository notedao;
	@Autowired
	private TypeRepository typedao;
	@Autowired
	private CourseRepository coursedao;

	@Override
	public Student findByUsername(String username) {
		// TODO Auto-generated method stub
		return studentDao.findByUsername(username);
	}

	@Override
	public Page<Note> findNoteByUsername(String username, Pageable pageable) {
		// TODO Auto-generated method stub
		return notedao.findNoteByUsername(username, pageable);
	}

	

	@Override
	public Note save(Note note) {
		// TODO Auto-generated method stub
		return notedao.save(note);
	}

	@Override
	public Note findNoteById(Long id) {
		// TODO Auto-generated method stub
		return notedao.findNoteById(id);
	}

	@Override
	public List<noteType> findAlltype() {
		// TODO Auto-generated method stub
		return typedao.findAll();
	}
	@Override
	public Page<Note> findNoteByUsername2(String username, Pageable pageable) {
		// TODO Auto-generated method stub
		return notedao.findNoteByUsername2(username, pageable);
	}

	@Override
	public Page<Course> findByCourseLike2(String stuUser, Pageable pageable) {
		// TODO Auto-generated method stub
		return coursedao.findByCourseLike2(stuUser, pageable);
	}

	@Override
	public List<Student> findByStudentPDF(String nameLike, String userLike, String collegeId, String majorId,
			String classId) {
		// TODO Auto-generated method stub
		return studentDao.findByStudentPDF(nameLike, userLike, collegeId, majorId, classId);
	}
	
}