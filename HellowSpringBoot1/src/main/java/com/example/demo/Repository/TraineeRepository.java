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
import com.example.demo.entity.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> , JpaSpecificationExecutor<Trainee>{


	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.stu_username,\r\n" + 
			"	t.course_id, t.opt, \r\n" + 
			"	( SELECT `name` FROM student WHERE username = t.stu_username ) AS stu_name,\r\n" + 
			"	( SELECT `name` FROM course WHERE id = t.course_id ) AS course_name,\r\n" + 
			"	( SELECT class_id FROM student WHERE username = t.stu_username ) AS class_id,\r\n" + 
			"	( SELECT `name` FROM lgclass WHERE id = ( SELECT class_id FROM student WHERE username = t.stu_username ) ) AS class_name \r\n" + 
			"FROM\r\n" + 
			"	trainee t \r\n" + 
			"WHERE\r\n" + 
			"IF\r\n" + 
			"	( ?1 != 0, t.course_id = ?1, 1 = 1 ) \r\n" + 
			"AND\r\n" + 
			"  ( SELECT `name` FROM student WHERE username = t.stu_username ) LIKE %?2% " ,
			 countQuery = " SELECT COUNT(*) FROM\r\n" + 
			 		"	trainee t \r\n" + 
			 		"WHERE\r\n" + 
			 		"IF\r\n" + 
			 		"	( ?1 != 0, t.course_id = ?1, 1 = 1 ) \r\n" + 
			 		"AND  ( SELECT `name` FROM student WHERE username = t.stu_username ) LIKE %?2% "
			)

	Page<Trainee> findByTraineeLike(Long id,String nameLike, Pageable pageable);
	

	@Transactional
	@Query(nativeQuery = true,value ="DELETE from trainee WHERE id = ?1 AND stu_username = ?2")
	@Modifying
	void delTraineeByIdAndStu(Long id, String username);
	
	@Transactional
	@Query(nativeQuery = true,value ="insert into trainee (id, course_id, stu_username) values (?1, ?1, ?2)")
	@Modifying
	void saveTrainee(Long id, String username);
}
