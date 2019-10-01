package com.intuit.craft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.exception.InternalServerException;
import com.intuit.craft.exception.RecordNotFoundException;
import com.intuit.craft.model.TweetEntity;
import com.intuit.craft.service.TweetService;

@EnableAutoConfiguration
@RestController
public class TweetController {
	@Autowired
	TweetService service;

	/** This method will post the Tweet that contains all info related to Users and Followers along with tweets msg**/
	@RequestMapping(value = "/tweet", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<TweetEntity> postTweet(@RequestBody TweetEntity tweet) throws InternalServerException {

		try {
			TweetEntity postedTweet = service.createOrUpdateTweet(tweet);
			return new ResponseEntity<TweetEntity>(postedTweet, new HttpHeaders(), HttpStatus.CREATED);
		} catch (Exception ex) {
			throw new InternalServerException(ex.getMessage());
		}
	}
	
	/** This method will help to get HomePageTImeline with limited follower list for a login user.
	 *   User can pass parameters offset start from 0 to 100 or 10 etc and pagination by the user choice
	 *   to fetch most recent tweet e.g. http://localhost:8080/homepage?offset=0&limit=2 **/
	@RequestMapping(value = "/homepage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getHomePageTwitsWithLimit(@RequestParam("offset") int offset,
															@RequestParam("limit") int limit) throws RecordNotFoundException {
		try {
			String listOfTweets = service.getHomePageTwitsWithPaginationLimit(offset, limit);
			if (listOfTweets != null && !listOfTweets.isEmpty()) {
				return new ResponseEntity<String>(listOfTweets, new HttpHeaders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception ex) {
			throw new RecordNotFoundException(ex.getMessage());
		}

	}
	

	
	/**  This method help to fetch all the tweets and very generic adhoc method **/
	@RequestMapping(value = "/feed", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<TweetEntity>> getLatestTweets() throws RecordNotFoundException {
		try {
			List<TweetEntity> listOfTweets = service.getAllTweets();
			if (listOfTweets != null && !listOfTweets.isEmpty()) {
				return new ResponseEntity<List<TweetEntity>>(listOfTweets, new HttpHeaders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<TweetEntity>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception ex) {
			throw new RecordNotFoundException(ex.getMessage());
		}

	}
}