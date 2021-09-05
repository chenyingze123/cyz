package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AddressRepository;
import com.example.demo.entity.Address;
import com.example.demo.service.AddressService;
@Service
public class AddressServiceImpl  implements AddressService{
	@Autowired
	private AddressRepository addressDao;
	@Override
	public List<Address> findAll() {
		return addressDao.findAll();
	}
	@Override
	public Address save(Address address) {
		
		return addressDao.save(address);
	}
	@Override
	public Address findByaddressName(String addressName) {
		
		return addressDao.findByAddressName(addressName);
	}
	@Override
	public Address edit(Address address) {
		return addressDao.save(address);
	}
	@Override
	public void deleteById(Long addressId) {
		addressDao.deleteById(addressId);
		
	}
	@Override
	public Address findByaddressId(Long addressId) {
		// TODO Auto-generated method stub
		return addressDao.findByAddressId(addressId);
	}
	

}
