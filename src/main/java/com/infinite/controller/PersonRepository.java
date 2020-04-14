package com.infinite.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	@Query(value = "SELECT * FROM person", 
	        nativeQuery=true
	    )
	    public List<Person> getAllPerson();

}
