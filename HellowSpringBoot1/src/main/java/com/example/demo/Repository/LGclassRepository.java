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

@Repository
public interface LGclassRepository extends JpaRepository<LGclass, Long> , JpaSpecificationExecutor<LGclass>{

	@Transactional
	@Query(nativeQuery = true,value ="	SELECT COUNT(*) FROM lgclass t WHERE t.isused ='Y' AND t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)AND IF (?3 !='0', t.major_id = ?3 , 1 = 1)")
	long countByLGclassLike(String nameLike,String collegeId,String majorId);
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.`name`,\r\n" + 
			"	t.message,\r\n" + 
			"	t.college_id,\r\n" + 
			"	t.major_id,\r\n" + 
			"	( SELECT `name` FROM college WHERE id = t.college_id ) AS college_name,\r\n" + 
			"	( SELECT `name` FROM major WHERE id = t.major_id ) AS major_name ,\r\n" + 
			"	t.isused,t.opt " + 
			"FROM\r\n" + 
			"	lgclass t \r\n" + 
			"WHERE\r\n" + 
			"	t.isused = 'Y' AND t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)AND IF (?3 !='0', t.major_id = ?3 , 1 = 1)" ,
			 countQuery = " SELECT COUNT(*) FROM lgclass t WHERE t.isused ='Y' AND t.`name` LIKE %?1% AND IF (?2 !='0', t.college_id = ?2 , 1 = 1)AND IF (?3 !='0', t.major_id = ?3 , 1 = 1)"
			)

	Page<LGclass> findByLGclassLike(String nameLike, String collegeId,String majorId, Pageable pageable);
	@Modifying
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
    LGclass findLGclassById(Long id);
    
	@Query(nativeQuery = true,value ="SELECT * FROM lgclass WHERE isused = 'Y' AND major_id = ?1")
	 List<LGclass> findLGclassList2(String id);
}
