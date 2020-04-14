package com.infinite.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.bean.Corona;

@RestController
@RequestMapping("/api/spring-rest")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CoronaService coronaService;
	
	static Map<String,Corona> coronaList=null;
	
	@GetMapping("/sample")
	public String getData() {
		System.out.println("AWS Rolling deployment post data");
		return "AWS Rolling deployment post data";
	}
	
	@GetMapping("/person/all")
	public List<Person> getPersons(){
      //  List<Person> persons = new ArrayList<Person>();
		
		return personRepository.getAllPerson();
		
	}
	
	@GetMapping("/person/{id}")
	public Optional<Person> getPersonById(@PathVariable long id){
      //  List<Person> persons = new ArrayList<Person>();
		System.out.println("id " + id);
		 Optional<Person> per= personRepository.findById(id);
		 return per;
	
	}
	
	@DeleteMapping("/person/remove/{id}")
	public  ResponseEntity<Void> removeEmployee(@Valid @PathVariable long id) {
		
			  personRepository.deleteById(id);
			  return ResponseEntity.noContent().build();
			 
	}
	
	@PostMapping("/person/add")
	public Person savePerson(@Valid @RequestBody Person per) {
		return personRepository.save(per);
		
	}
	

	
	@GetMapping("/infinite-zero/corona/all")
	public Map<String,Corona> getCoronaData()
	{
		 
		  return coronaService.getCoronaData();
	}
	
	@GetMapping("/infinite-zero/corona/all/sort/{sorttype}/{limit}")
	public Map<String,Corona> getCoronaSortedData(@PathVariable String sorttype,@PathVariable int limit)
	{
		System.out.println("sorttype " + sorttype +" limit " + limit);
		return coronaService.getCoronaSortedData(sorttype,limit);
		
		
	}
	@GetMapping("/infinite-zero/corona/country/{countryname}")
	public Corona getCoronaCountryData(@PathVariable String countryname)
	{
		System.out.println("sorttype " + countryname);
		return coronaService.getCoronaCountryData(countryname);
		
		
	}
}
