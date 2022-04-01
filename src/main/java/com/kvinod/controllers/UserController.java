package com.kvinod.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kvinod.entity.User;
import com.kvinod.repository.UserRepository;
import com.kvinod.repository.UserCustomDAL;
import com.kvinod.exception.InvoiceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository repo;

	@Autowired
	UserCustomDAL mt_repo;

	@Cacheable(value = "users")
	@GetMapping
	public List<User> get() {
		log.info("returning list of all users");
		List<User> result = repo.findAll();
		Collections.sort(result);
		return result;
	}

	@Cacheable(value = "users", key = "#id")
	@GetMapping("/id/{id}")
	public User get(@PathVariable String id) {
		
		log.info("returning user for id " + id);
		User u = repo.findById(id)
         .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));

		return u;
	}


	// @Cacheable(value = "users", key = "#phone")
	@GetMapping("/phone/{phone}")
	public List<User> get2(@PathVariable String phone) {
		log.info("returning user for phone " + phone);
		return repo.findByPhone(phone);
	}

	@Cacheable(value="users", key="#city")
	@GetMapping("/city/")
	public List<User> getCity(@RequestParam(name="city") String city) {
		log.info("returning users for city " + city);
		return repo.findByCity(city);
	}


	@GetMapping("/city_n_phone/")
	public User mt_findCityAndPhone(@RequestParam(name="city") String city, @RequestParam(name="phone") String phone) {
		return mt_repo.FindByCityAndPhone(city, phone);
	}

	@GetMapping("/city_n_lastname/")
	public List<User> mt_findCityAndLastname(@RequestParam(name="city") String city, @RequestParam(name="lastname") String lastname) {
		return mt_repo.FindByCityAndLastname(city, lastname);
	}

	@GetMapping("/use_in/")
	public List<User> mt_useIn() {
		return mt_repo.useIn();
	}

	@CacheEvict(value="users")
	@PostMapping
	public User save(@RequestBody User user) {
		log.info("saving user details: " + user.toString());
		repo.save(user);
		return user;
	}

	@CachePut(value="users", key="#id")
	@PutMapping
	public User modify(@RequestParam(name="id") String id, @RequestBody User user) {
		log.info("modify user details: " + user.toString());
		
		User modifUser = repo.findById(id).get();
		modifUser.setName(user.getName());
		modifUser.setPhone(user.getPhone());
		modifUser.setCity(user.getCity());
		modifUser.setEmail(user.getEmail());
		repo.save(modifUser);
		return modifUser;
	}	

}
