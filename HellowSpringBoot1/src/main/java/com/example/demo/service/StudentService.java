package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Course;
import com.example.demo.entity.Note;
import com.example.demo.entity.Student;
import com.example.demo.entity.noteType;

import aj.org.objectweb.asm.Type;

public interface StudentService {

   Student findByUsername(String username);
   Page<Note> findNoteByUsername(String username,Pageable pageable);
   Note save(Note note);
   Note findNoteById(Long id);
   List<noteType> findAlltype();
   Page<Note> findNoteByUsername2(String username,Pageable pageable);
   Page<Course> findByCourseLike2(String stuUser, Pageable pageable);
   List<Student> findByStudentPDF(String nameLike,String userLike, String collegeId,String majorId,String classId);
}
