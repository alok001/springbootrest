package com.infinite.controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

@Entity
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message="name cannot be empty")
	private String name;
	@NotEmpty(message="age cannot be empty")
	private String age;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message="address cannot be empty")
	private String address;
	@NotEmpty(message="city cannot be empty")
	private String city;
	
	public Person() {
		
	}
	public Person(Long id,String name,String age,String address, String city){
		this.name=name;
		this.address=address;
		this.age=age;
		this.city=city;
		this.id=id;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
