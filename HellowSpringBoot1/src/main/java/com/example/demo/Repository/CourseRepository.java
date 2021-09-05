package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.entity.College;
import com.example.demo.entity.Course;
import com.example.demo.entity.LGclass;
import com.example.demo.entity.Major;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> , JpaSpecificationExecutor<Course>{


	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.message,\r\n" + 
			"	t.isused,\r\n" + 
			"	t.kind_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"	(SELECT `name` FROM college WHERE id = t.college_id)AS college_name,\r\n" + 
			"	(SELECT `name` FROM kind WHERE id = t.kind_id)AS kind_name,\r\n" + 
			"	t.opt,\r\n" + 
			"	t.teacher_id,\r\n" + 
			"	(SELECT `name` FROM teacher WHERE  id = t.teacher_id)AS teacher_name \r\n" + 
			"FROM\r\n" + 
			"	course t\r\n" + 
			"	WHERE t.`name` LIKE %?1%  AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)  "
			+ "AND IF (?3 !='0', t.teacher_id = ?3 , 1 = 1) " ,
			 countQuery = " SELECT COUNT(*) FROM course t WHERE  t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1) AND IF (?3 !='0', t.teacher_id = ?3 , 1 = 1)"
			)

	Page<Course> findByCourseLike(String nameLike,String collegeId,  String teacherId, Pageable pageable);
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.message,\r\n" + 
			"	t.isused,\r\n" + 
			"	t.kind_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"	(SELECT `name` FROM college WHERE id = t.college_id)AS college_name,\r\n" + 
			"	(SELECT `name` FROM kind WHERE id = t.kind_id)AS kind_name,\r\n" + 
			"	t.opt,\r\n" + 
			"	t.teacher_id,\r\n" + 
			"	(SELECT `name` FROM teacher WHERE  id = t.teacher_id)AS teacher_name \r\n" + 
			"FROM\r\n" + 
			"	course t\r\n" + 
			"	WHERE ( SELECT stu_username FROM trainee WHERE id = t.id ) =  ?1" ,
			 countQuery = " SELECT COUNT(*) FROM course t WHERE  ( SELECT stu_username FROM trainee WHERE id = t.id ) = ?1"
			)

	Page<Course> findByCourseLike2(String stuUser, Pageable pageable);
	/*@Transactional
	@Query(nativeQuery = true,value ="	SELECT * FROM teacher WHERE college_id = ?1")
	List<Teacher> findTeacherByCollegeId(String id);*/
	
}
