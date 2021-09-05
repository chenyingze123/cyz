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
import com.example.demo.entity.LGclass;
import com.example.demo.entity.Major;
import com.example.demo.entity.Note;
import com.example.demo.entity.Situation;
import com.example.demo.entity.Student;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> , JpaSpecificationExecutor<Situation>{


	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.teacher_id,\r\n" + 
			"	t.course_id,\r\n" + 
			"	t.starttime,\r\n" + 
			"	t.message,\r\n" + 
			"	( SELECT `name` FROM course WHERE id = t.course_id ) AS course_name,\r\n" + 
			"	( SELECT `name` FROM teacher WHERE id = t.teacher_id ) AS teacher_name \r\n" + 
			"FROM\r\n" + 
			"	situation t \r\n" + 
			"WHERE\r\n" + 
			"	( SELECT `name` FROM course WHERE id = t.course_id ) LIKE %?1% \r\n" + 
			"AND\r\n" + 
			"IF\r\n" + 
			"	( ?2 != 0, t.teacher_id = ?2, 1 = 1 ) \r\n" + 
			"AND\r\n" + 
			"IF\r\n" + 
			"	( ?3 != 0, t.course_id = ?3, 1 = 1 )" ,
			 countQuery = "SELECT\r\n" + 
			 		"	count(*) \r\n" + 
			 		"FROM\r\n" + 
			 		"	situation t \r\n" + 
			 		"WHERE\r\n" + 
			 		"	( SELECT `name` FROM course WHERE id = t.course_id ) LIKE %?1% \r\n" + 
			 		"AND\r\n" + 
			 		"IF\r\n" + 
			 		"	( ?2 != 0, t.teacher_id = ?2, 1 = 1 ) \r\n" + 
			 		"AND\r\n" + 
			 		"IF\r\n" + 
			 		"	( ?3 != 0, t.course_id = ?3, 1 = 1 )"
			)

	Page<Situation> findBySituationLike(String nameLike,String teacherId, String courseId, Pageable pageable);

	
	

}
