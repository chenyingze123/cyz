package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.entity.noteType;

import aj.org.objectweb.asm.Type;

@Repository
public interface TypeRepository extends JpaRepository<noteType, Long> {

	
}
