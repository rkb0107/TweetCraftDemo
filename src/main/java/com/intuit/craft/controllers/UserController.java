package com.intuit.craft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.exception.InternalServerException;
import com.intuit.craft.model.UserEntity;
import com.intuit.craft.service.UserService;

@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

	/** This controller method helps to create or update the Login user info**/
	@PutMapping
	public ResponseEntity<UserEntity> createOrUpdateUser(@RequestBody UserEntity User) throws InternalServerException {
		try {
			UserEntity updated = service.createOrUpdateUser(User);
			return new ResponseEntity<UserEntity>(updated, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			throw new InternalServerException(ex.getMessage());
		}

	}

}