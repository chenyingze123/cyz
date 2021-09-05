package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
/*	@Transactional
	@Query("select * from admin where username= ?1  ")
    @Modifying
     public List<Admin> findByLike(String likes);*/
	
	 public List<Admin> findByUsernameContaining(String key);
	 public List<Admin> findByRealNameContaining(String key);
      Admin findByUsername(String username);
      public List<Admin> findByUsernameAndPassword(String username,String password);
      //public Admin findByUsernameAndPassword(String username,String password);
	    //@Transactional
	    //@Modifying
	    //@Query("update admin set password = ?1 where username = ?2")
	    //void updataByUsername(String password, String username);//更新id为传入id的用户的名称为name
    /*  @Transactional
      @Modifying
      @Query("delete from User where id = ?1")
      void deleteById(Long id);*/
      @Query("delete from Admin where realName = ?1")
      @Modifying
      public void deleteByRealName(String realName);    
      @Query("select u from Admin u where u.username =?1")
       public List<Admin> findLike(String key);
        Page<Admin> findAll(Pageable pageable);
        
        
      
}