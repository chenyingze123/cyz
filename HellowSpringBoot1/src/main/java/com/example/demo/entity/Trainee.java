package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.filefilter.FalseFileFilter;

@Entity
@Table(name="trainee")

public class Trainee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	//@Column(nullable = false,unique = )	
	private String stuUsername;
	private Long courseId;
	private String stuName;
	private String courseName;
	private Long classId;
	private String className;
	private String opt;
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	//private Long coll
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getStuUsername() {
		return stuUsername;
	}
	public void setStuUsername(String stuUsername) {
		this.stuUsername = stuUsername;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	

	
	
	

}
