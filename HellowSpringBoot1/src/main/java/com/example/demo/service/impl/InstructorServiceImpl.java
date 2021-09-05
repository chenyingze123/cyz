package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.InstructorRepository;
import com.example.demo.Repository.NoteRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.TypeRepository;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.Note;
import com.example.demo.entity.Student;
import com.example.demo.entity.noteType;
import com.example.demo.service.AdminService;
import com.example.demo.service.InstructorService;
import com.example.demo.service.StudentService;

import aj.org.objectweb.asm.Type;

@Service
public class InstructorServiceImpl implements  InstructorService{

	@Autowired
	private StudentRepository studentDao;
	@Autowired
	private NoteRepository notedao;
	@Autowired
	private TypeRepository typedao;
	@Autowired
	private InstructorRepository instrdao;
	@Override
	public Instructor findInstructorByUsername(String username) {
		// TODO Auto-generated method stub
		return instrdao.findInstructorByUsername(username);
	}

	
	
}