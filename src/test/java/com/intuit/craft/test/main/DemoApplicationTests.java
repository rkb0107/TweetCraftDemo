package com.intuit.craft.test.main;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoApplicationTests.class})
public class DemoApplicationTests {
	  final String uri = "http://localhost:8080";
	
	  
	//@Test // All the get call are blocked from test case should be access from UI only
	public void getfeed() 
	{
	  
	     Response response = RestAssured
	    				.given()
	    					.baseUri(uri)
	    					.contentType("application/json")
	    					.auth().form("rakesh", "abcd123")
	    				.when()
	    					.get("/feed")
	    				.then()
	    					.assertThat()
	    					.statusCode(200)
	    					.and()
	    					.extract().response();
	    String body = response.body().asString();
	    System.out.println(body);
	    Assert.assertNotNull(body);
	}
	
	// Post Method for USER data insertion
	@Test
	public void PostUserData() {
			String userData = "{\"userId\":1,\"userName\":\"rakesh\"}";
			  Response response = RestAssured
	    				.given()
	    					.baseUri(uri + "/user")
	    					.contentType("application/json")
	    					.body(userData)
	    				.then()
	    					.put();
	    String body = response.asString();
	    System.out.println(body);
			
	}
	
	// Put method for Follower to add new records
	@Test
	public void followData() {
			String userData = "{\"followId\":1,\"followName\":\"Mahesh\"}";
			
			  Response response = RestAssured
	    				.given()
	    					.baseUri(uri + "/follow")
	    					.contentType("application/json")
	    					.body(userData)
	    				.then()
	    					.put();
	    String body = response.asString();
	    System.out.println(body);
			
	}
	
	// Put method for TWEET.(Note: This should be in the last because it has foreign key constraints)
		@Test
		public void tweetData() {
				String userData = "{\"twitId\":1,\"userId\":1,\"followId\":2,\"msgTxt\":\"hi\",\"publishDate\":\"2019-09-30 22:44:50.782\"}";
				  Response response = RestAssured
		    				.given()
		    					.baseUri(uri + "/tweet")
		    					.contentType("application/json")
		    					.body(userData)
		    				.then()
		    					.post();
		    String body = response.asString();
		    System.out.println(body);
				
		}
}



