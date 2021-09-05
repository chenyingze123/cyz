package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;



@Entity
@Table(name="admin") 

public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false,unique = true)
private String username;
private String password;


private String realName;

private String sex;

private String telephone;

private String address;

private String mail;
private String addressName;

public String getAddressName() {
	return addressName;
}

public void setAddressName(String addressName) {
	this.addressName = addressName;
}

/*private String addressName;*/
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRealName() {
	return realName;
}

public void setRealName(String realName) {
	this.realName = realName;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public String getTelephone() {
	return telephone;
}

public void setTelephone(String telephone) {
	this.telephone = telephone;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}
/*
public String getAddressName() {
	return addressName;
}

public void setAddressName(String addressName) {
	this.addressName = addressName;
}*/





}
