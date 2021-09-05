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
import com.example.demo.entity.Student;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> , JpaSpecificationExecutor<Note>{


	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.starttime,\r\n" + 
			"	t.endtime,\r\n" + 
			"	t.stu_username,\r\n" + 
			"	( SELECT `name` FROM student WHERE username = t.stu_username ) AS stu_name,\r\n" + 
			"	t.major_id,\r\n" + 
			"	t.class_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"	( SELECT `name` FROM major WHERE id = t.major_id ) AS major_name,\r\n" + 
			"	( SELECT `name` FROM lgclass WHERE id = t.class_id ) AS class_name,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name,\r\n" + 
			"	t.instr_username,\r\n" + 
			"	( SELECT `name` FROM instructor WHERE username = t.instr_username ) AS instr_name,\r\n" + 
			"	t.result,\r\n" + 
			"	t.type_id, t.grade, \r\n" + 
			"	( SELECT `name` FROM type WHERE id = t.type_id ) AS type_name , t.message\r\n" + 
			"FROM\r\n" + 
			"	note t \r\n" + 
			"WHERE\r\n" + 
			"	t.stu_username LIKE %?2%  AND t.result LIKE %?6%  \r\n" + 
			"	AND ( SELECT `name` FROM student WHERE username = t.stu_username ) LIKE %?1% \r\n" + 
			"AND\r\n" + 
			"IF\r\n" + 
			"	( ?3 != 0, t.college_id = ?3, 1 = 1 ) \r\n" + 
			"AND\r\n" + 
			"IF\r\n" + 
			"	( ?4 != 0, t.major_id = ?4, 1 = 1 ) \r\n" + 
			"AND\r\n" + 
			"IF\r\n" + 
			"	( ?5 != 0, t.class_id = ?5, 1 = 1 )  and t.result != '撤回' " ,
			 countQuery = "SELECT\r\n" + 
			 		"	count(*) \r\n" + 
			 		"FROM\r\n" + 
			 		"	note t \r\n" + 
			 		"WHERE\r\n" + 
			 		"	t.stu_username LIKE %?2%  AND t.result LIKE %?6% \r\n" + 
			 		"	AND ( SELECT `name` FROM student WHERE username = t.stu_username ) LIKE %?1% \r\n" + 
			 		"AND\r\n" + 
			 		"IF\r\n" + 
			 		"	( ?3 != 0, t.college_id = ?3, 1 = 1 ) \r\n" + 
			 		"AND\r\n" + 
			 		"IF\r\n" + 
			 		"	( ?4 != 0, t.major_id = ?4, 1 = 1 ) \r\n" + 
			 		"AND\r\n" + 
			 		"IF\r\n" + 
			 		"	( ?5 != 0, t.class_id = ?5, 1 = 1 ) and t.result != '撤回' "
			)

	Page<Note> findByNoteLike(String nameLike,String userLike, String collegeId,String majorId,String classId,String result, Pageable pageable);

	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.starttime,\r\n" + 
			"	t.endtime,\r\n" + 
			"	t.stu_username,\r\n" + 
			"	( SELECT `name` FROM student WHERE username = t.stu_username ) AS stu_name,\r\n" + 
			"	t.major_id,\r\n" + 
			"	t.class_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"	( SELECT `name` FROM major WHERE id = t.major_id ) AS major_name,\r\n" + 
			"	( SELECT `name` FROM lgclass WHERE id = t.class_id ) AS class_name,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name,\r\n" + 
			"	t.instr_username,\r\n" + 
			"	( SELECT `name` FROM instructor WHERE username = t.instr_username ) AS instr_name,\r\n" + 
			"	t.result,\r\n" + 
			"	t.type_id , t.grade ,\r\n" + 
			"	( SELECT `name` FROM type WHERE id = t.type_id ) AS type_name , t.message\r\n" + 
			"FROM\r\n" + 
			"	note t \r\n" + 
			"WHERE\r\n" + 
			"t.stu_username = ?1 " ,
			 countQuery = "SELECT\r\n" + 
			 		"	count(*) \r\n" + 
			 		"FROM\r\n" + 
			 		"	note t \r\n" + 
			 		"WHERE\r\n" + 
			 		"	t.stu_username = ?1   "
			)

	Page<Note> findNoteByUsername(String username,Pageable pageable);
	@Transactional
	@Query(nativeQuery = true,value = "select * from note where id = ?1")
	Note findNoteById(Long id);
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.starttime,\r\n" + 
			"	t.endtime,\r\n" + 
			"	t.stu_username,\r\n" + 
			"	( SELECT `name` FROM student WHERE username = t.stu_username ) AS stu_name,\r\n" + 
			"	t.major_id,\r\n" + 
			"	t.class_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"	( SELECT `name` FROM major WHERE id = t.major_id ) AS major_name,\r\n" + 
			"	( SELECT `name` FROM lgclass WHERE id = t.class_id ) AS class_name,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name,\r\n" + 
			"	t.instr_username,\r\n" + 
			"	( SELECT `name` FROM instructor WHERE username = t.instr_username ) AS instr_name,\r\n" + 
			"	t.result,\r\n" + 
			"	t.type_id , t.grade ,\r\n" + 
			"	( SELECT `name` FROM type WHERE id = t.type_id ) AS type_name , t.message\r\n" + 
			"FROM\r\n" + 
			"	note t \r\n" + 
			"WHERE\r\n" + 
			"t.stu_username = ?1 AND T.result LIKE '%通过%'" ,
			 countQuery = "SELECT\r\n" + 
			 		"	count(*) \r\n" + 
			 		"FROM\r\n" + 
			 		"	note t \r\n" + 
			 		"WHERE\r\n" + 
			 		"	t.stu_username = ?1   "
			)

	Page<Note> findNoteByUsername2(String username,Pageable pageable);
	

}
