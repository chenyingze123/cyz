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
import com.example.demo.entity.Instructor;
import com.example.demo.entity.LGclass;
import com.example.demo.entity.Major;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> , JpaSpecificationExecutor<Instructor>{


	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.username,\r\n" + 
			"	t.`password`,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.college_id,\r\n" + 
			"IF\r\n" + 
			"	( t.sex = 1, '男', '女' ) AS sex,\r\n" + 
			"	t.address,\r\n" + 
			"	t.telephone,\r\n" + 
			"	t.mail, t.grade , \r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name \r\n" + 
			"FROM\r\n" + 
			"	instructor t \r\n" + 
			"WHERE\r\n" + 
			"	t.`name` LIKE %?1% AND t.username LIKE %?2%  \r\n" + 
			"AND\r\n" + 
			"IF\r\n" + 
			"	( ?3 != 0, t.college_id = ?3, 1 = 1 ) \r\n" + 
			"" ,
			 countQuery = " SELECT COUNT(*) FROM instructor t WHERE  t.`name` LIKE %?1%  AND t.username LIKE %?2% AND IF (?3 !='0', t.college_id = ?3 , 1 = 1)"
			)

	Page<Instructor> findByInstructorLike(String nameLike,String userLike, String collegeId, Pageable pageable);
	
	@Transactional
	@Query(nativeQuery = true,value = "select * from instructor where username = ?1")
	Instructor findInstructorByUsername(String username);
}
