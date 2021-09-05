package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	Address findByAddressName(String addressName);
	@Query(nativeQuery = true,value ="SELECT\r\n" + 
			"	*\r\n" + 
			"FROM\r\n" + 
			"	Address t\r\n" + 
			"WHERE\r\n" + 
			"	t.address_id = ?1")
	Address findByAddressId(Long addressId);
	
}
