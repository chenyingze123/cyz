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
import com.example.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student>{

	@Transactional
	@Query(nativeQuery = true,value ="	SELECT COUNT(*) FROM student t WHERE  t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)AND IF (?3 !='0', t.major_id = ?3 , 1 = 1) AND IF (?4 !='0', t.class_id = ?4 , 1 = 1)")
	long countByStudentLike(String nameLike,String collegeId,String majorId,String classId);
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.username,\r\n" + 
			"	t.`password`,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.major_id,\r\n" + 
			"	t.class_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"IF\r\n" + 
			"	( t.sex = 1, '男', '女' ) AS sex,\r\n" + 
			"	t.address,\r\n" + 
			"	telphone,\r\n" + 
			"	t.mail,\r\n" + 
			"	( SELECT `name` FROM major WHERE id = t.major_id ) AS major_name,\r\n" + 
			"	( SELECT `name` FROM lgclass WHERE id = t.class_id ) AS class_name,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name \r\n" + 
			"FROM\r\n" + 
			"	student t \r\n" + 
			"WHERE\r\n" + 
			"  t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)AND IF (?3 !='0', t.major_id = ?3 , 1 = 1) AND IF (?4 !='0', t.class_id = ?4 , 1 = 1)" ,
			 countQuery = " SELECT COUNT(*) FROM student t WHERE  t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)AND IF (?3 !='0', t.major_id = ?3 , 1 = 1) AND IF (?4 !='0', t.class_id = ?4 , 1 = 1)"
			)

	Page<Student> findByStudentLike(String nameLike, String collegeId,String majorId,String classId, Pageable pageable);
/*	@Modifying
    @Transactional
	@Query(nativeQuery = true,value ="UPDATE lgclass SET isused = 'N' WHERE id = ?1")
    void delLGclassById(Long id);
	
    @Transactional
	@Query(nativeQuery = true,value ="SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.message,\r\n" + 
			"	t.college_id,\r\n" + 
			"	t.major_id,\r\n" + 
			"	t.isused,\r\n" + 
			"	( SELECT `name` FROM major WHERE t.major_id = id ) AS major_name,\r\n" + 
			"	( SELECT `name` FROM college WHERE t.college_id = id ) AS college_name, \r\n" + 
			"t.opt FROM\r\n" + 
			"	lgclass t \r\n" + 
			"WHERE\r\n" + 
			"	id = ?1")
    LGclass findLGclassById(Long id);*/
	@Transactional
	@Query(nativeQuery = true,value ="	SELECT COUNT(*) FROM student t WHERE  t.`name` LIKE %?1%  AND t.username LIKE %?2% AND IF (?3 !='0', t.college_id = ?3 , 1 = 1)AND IF (?4 !='0', t.major_id = ?4 , 1 = 1) AND IF (?5 !='0', t.class_id = ?5 , 1 = 1)")
	long countByStudentLike2(String nameLike,String userLike,String collegeId,String majorId,String classId);
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.username,\r\n" + 
			"	t.`password`,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.major_id,\r\n" + 
			"	t.class_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"IF\r\n" + 
			"	( t.sex = 1, '男', '女' ) AS sex,\r\n" + 
			"	t.address,\r\n" + 
			"	telphone,\r\n" + 
			"	t.mail,\r\n" + 
			"	( SELECT `name` FROM major WHERE id = t.major_id ) AS major_name,\r\n" + 
			"	( SELECT `name` FROM lgclass WHERE id = t.class_id ) AS class_name,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name \r\n" + 
			"FROM\r\n" + 
			"	student t \r\n" + 
			"WHERE\r\n" + 
			"  t.`name` LIKE %?1%  AND t.username LIKE %?2% AND IF (?3 !='0', t.college_id = ?3 , 1 = 1)AND IF (?4 !='0', t.major_id = ?4 , 1 = 1) AND IF (?5 !='0', t.class_id = ?5 , 1 = 1)" ,
			 countQuery = " SELECT COUNT(*) FROM student t WHERE  t.`name` LIKE %?1%  AND t.username LIKE %?2% AND IF (?3 !='0', t.college_id = ?3 , 1 = 1)AND IF (?4 !='0', t.major_id = ?4 , 1 = 1) AND IF (?5 !='0', t.class_id = ?5 , 1 = 1)"
			)
	Page<Student> findByStudentLike2(String nameLike,String userLike, String collegeId,String majorId,String classId, Pageable pageable);
	@Transactional
	@Query(nativeQuery = true,value ="SELECT * from student WHERE username = ?1")
	Student findByUsername(String username);
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.username,\r\n" + 
			"	t.`password`,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.major_id,\r\n" + 
			"	t.class_id,\r\n" + 
			"	t.college_id,\r\n" + 
			"IF\r\n" + 
			"	( t.sex = 1, '男', '女' ) AS sex,\r\n" + 
			"	t.address,\r\n" + 
			"	telphone,\r\n" + 
			"	t.mail,\r\n" + 
			"	( SELECT `name` FROM major WHERE id = t.major_id ) AS major_name,\r\n" + 
			"	( SELECT `name` FROM lgclass WHERE id = t.class_id ) AS class_name,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name \r\n" + 
			"FROM\r\n" + 
			"	student t \r\n" + 
			"WHERE\r\n" + 
			"  t.`name` LIKE %?1%  AND t.username LIKE %?2% AND IF (?3 !='0', t.college_id = ?3 , 1 = 1)AND IF (?4 !='0', t.major_id = ?4 , 1 = 1) AND IF (?5 !='0', t.class_id = ?5 , 1 = 1)" 
			
			)
	List<Student> findByStudentPDF(String nameLike,String userLike, String collegeId,String majorId,String classId);
}
