package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Admin;



public interface AdminPageRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin>{

	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.username,\r\n" + 
			"	t. PASSWORD,\r\n" + 
			"	t.real_name,\r\n" + 
			"	t.sex,\r\n" + 
			"	t.telephone,\r\n" + 
			"	t.address,\r\n" + 
			"	t.mail,\r\n" + 
			"	tb.address_name AS address_name\r\n" + 
			"FROM\r\n" + 
			"	Admin t\r\n" + 
			"LEFT JOIN Address tb ON tb.address_id = t.address",
			 countQuery = "SELECT count(*) FROM Admin "
			)
	Page<Admin> findAll(@Nullable Specification<Admin> spec,Pageable pageable);
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id,\r\n" + 
			"	t.username,\r\n" + 
			"	t. PASSWORD,\r\n" + 
			"	t.real_name,\r\n" + 
			"	t.sex,\r\n" + 
			"	t.telephone,\r\n" + 
			"	t.address,\r\n" + 
			"	t.mail,\r\n" + 
			"	tb.address_name AS address_name\r\n" + 
			"FROM\r\n" + 
			"	Admin t\r\n" + 
			"LEFT JOIN Address tb ON tb.address_id = t.address",
			 countQuery = "SELECT count(*) FROM Admin "
			)
	Page<Admin> findAll(Pageable pageable);
	@Query(nativeQuery = true,value ="SELECT count(*) FROM Admin")
	long count();
	
	@Transactional
	@Query(nativeQuery = true,value = "SELECT\r\n" + 
			"	t.id AS id,\r\n" + 
			"	t.username AS username,\r\n" + 
			"	t.`password` AS `password`,\r\n" + 
			"	t.real_name AS real_name,\r\n" + 
			"	t.sex AS sex,\r\n" + 
			"	t.telephone AS telephone,\r\n" + 
			"	t.address AS address,\r\n" + 
			"	t.mail AS mail,\r\n" + 
			"	tb.address_name AS address_name\r\n" + 
			"FROM\r\n" + 
			"	Admin t\r\n" + 
			"LEFT JOIN Address tb ON tb.address_id = t.address\r\n" + 
			"WHERE\r\n" + 
			"	t.username LIKE %?1% ",
			 countQuery = "SELECT\r\n" + 
			 		"	count(*)\r\n" + 
			 		"FROM\r\n" + 
			 		"	Admin\r\n" + 
			 		"WHERE\r\n" + 
			 		"	username LIKE %?1%"
			)
	Page<Admin> findByUserLike(String userLike,Pageable pageable);
	@Transactional
	@Query(nativeQuery = true,value ="SELECT\r\n" + 
			"	count(*)\r\n" + 
			"FROM\r\n" + 
			"	Admin\r\n" + 
			"WHERE\r\n" + 
			"	username LIKE %?1%")
	long countByUserLike(String userLike);

}
