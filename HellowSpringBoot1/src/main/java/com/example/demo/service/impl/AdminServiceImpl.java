package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AdminRepository;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminDao;

	@Override
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	@Override
	public Admin save(Admin admin) {
		return adminDao.save(admin);

	}

	@Override
	public void deleteById(Long id) {
		 adminDao.deleteById(id);
	}

	@Override
	public Admin edit(Admin admin) {
		//System.out.println("111");
		return adminDao.save(admin);
	}
/*
	@Override
	public List<Admin> findByUsername(String username) {
		// TODO Auto-generated method stub
		return adminDao;
	}
*/

	@Override
	public Admin findByUsername(String username) {
		// TODO Auto-generated method stub
		
		return adminDao.findByUsername(username);
	}
	/*@Override
	public Admin LoginAdmin(String username, String password) {
		// TODO Auto-generated method stub
		 return adminDao.findByUsernameAndPassword(username, password);
	}*/

/*@Override
public List<Admin> LoginAdmin(String username, String password) {
	// TODO Auto-generated method stub
	 return adminDao.findByUsernameAndPassword(username, password);
}*/

	@Override
	public boolean Login(Admin admin) {
		 List<Admin> userList = adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
	      return userList.size()>0;
	}

	@Override
	public List<Admin> findByLike(String key) {

		return adminDao.findByRealNameContaining(key);
	}

	
	

	

	
}