package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AdminPageRepository;
import com.example.demo.entity.Admin;
import com.example.demo.service.PageService;
@Service
public class PageServiceImpl  implements PageService{
 @Autowired
 private AdminPageRepository admindao;
	/*@Override
	public Page<Admin> findAdminAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return admindao.findAll(pageable);
	}*/

	@Override
	public Page<Admin> findAdminByUserLike( String userLike,Pageable pageable) {
		// TODO Auto-generated method stub
		//return admindao.findByUserLike(userLike, pageable);
		if (userLike == null) {
			return admindao.findAll(pageable);
		}else {
			return admindao.findByUserLike(userLike, pageable);
		}
	}

	@Override
	public long countByUserLike(String userLike) {
		if (userLike == null) {
			return admindao.count();
		}else {
			return admindao.countByUserLike(userLike);
		}
		
		
		
	}

	/*@Override
	public long countAll() {
		
		return admindao.count();
	}
*/
}
