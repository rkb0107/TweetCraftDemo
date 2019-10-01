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
import com.intuit.craft.model.FollowEntity;
import com.intuit.craft.service.FollowService;

@EnableAutoConfiguration
@RestController
@RequestMapping("/follow")
public class FollowController {
	@Autowired
	FollowService service;

	/** This Method is used to create and update the follower information**/
	@PutMapping
	public ResponseEntity<FollowEntity> createOrUpdateFollow(@RequestBody FollowEntity Follow) throws InternalServerException {
		try {
			FollowEntity updated = service.createOrUpdateFollow(Follow);
			return new ResponseEntity<FollowEntity>(updated, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			throw new InternalServerException(ex.getMessage());
		}

	}

}