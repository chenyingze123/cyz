package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Admin;

public interface AdminService {
    List<Admin> findAll();
    Admin save(Admin admin);
    void deleteById(Long id);
    Admin edit(Admin admin);
    //List<Admin> findByUsername(String username);
   Admin findByUsername(String username);
   //Admin  edit(Admin admin);
//   List<Admin> LoginAdmin(String username,String password);
  //Admin LoginAdmin(String username,String password);
   boolean Login(Admin admin);
  List<Admin> findByLike( String key);
}
