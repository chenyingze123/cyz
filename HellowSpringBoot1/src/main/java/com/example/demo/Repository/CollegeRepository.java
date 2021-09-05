package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
	/*Address findByAddressName(String addressName);
	@Query(nativeQuery = true,value ="SELECT\r\n" + 
			"	*\r\n" + 
			"FROM\r\n" + 
			"	Address t\r\n" + 
			"WHERE\r\n" + 
			"	t.address_id = ?1")
	Address findByAddressId(Long addressId);*/
 	@Query(nativeQuery = true,value ="SELECT * FROM college WHERE isused = 'Y'")
	 List<College> findCollegeList();
 	
 	@Query(nativeQuery = true,value ="SELECT * FROM college WHERE isused = 'Y' And name = ?1")
	 List<College> findCollegeByName(String name);
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value ="UPDATE college SET isused = 'N' WHERE id = ?1")
    void delCollegeById(Long id);
}
