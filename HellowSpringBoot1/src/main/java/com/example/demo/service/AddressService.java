package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Address;



public interface AddressService {
	List<Address> findAll();
	Address save(Address address);
	Address findByaddressName(String addressName);
	Address edit(Address address);
	void deleteById(Long addressId);
	Address findByaddressId(Long addressId);
}
