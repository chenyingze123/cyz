package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.Note;
import com.example.demo.entity.Student;
import com.example.demo.entity.noteType;

import aj.org.objectweb.asm.Type;

public interface InstructorService {

	Instructor findInstructorByUsername(String username);
}
