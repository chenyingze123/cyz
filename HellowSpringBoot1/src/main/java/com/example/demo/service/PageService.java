package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Admin;


public interface PageService {
/*	Page<Admin> findAdminAll(Pageable pageable);*/
    Page<Admin> findAdminByUserLike(String userLike,Pageable pageable);
    long countByUserLike(String userLike);
    /*long countAll();*/
    
}
