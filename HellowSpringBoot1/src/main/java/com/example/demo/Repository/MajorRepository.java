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
import com.example.demo.entity.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> , JpaSpecificationExecutor<Major>{

	@Transactional
	@Query(nativeQuery = true,value ="SELECT COUNT(*) FROM major t WHERE t.isused = 'Y' AND t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)")
	long countByMajorLike(String nameLike,String collegeId);
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id AS id,\r\n" + 
			"	t.`name` AS `name`,\r\n" + 
			"	t.message AS message,\r\n" + 
			"	t.college_id AS college_id,\r\n" + 
			"	t.isused AS isused,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name \r\n" + 
			"FROM\r\n" + 
			"	major t \r\n" + 
			"WHERE\r\n" + 
			"	t.isused = 'Y' AND t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1) " ,
			 countQuery = " SELECT COUNT(*) FROM major t WHERE t.isused = 'Y' AND t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1) "
			)

	Page<Major> findByMajorLike(String nameLike, String collegeId, Pageable pageable);
	
	@Query(nativeQuery = true,value ="SELECT * FROM major WHERE isused = 'Y' AND college_id = ?1")
	 List<Major> findmajorList2(String id);
	@Modifying
    @Transactional
	@Query(nativeQuery = true,value ="UPDATE major SET isused = 'N' WHERE id = ?1")
    void delMajorById(Long id);
}
