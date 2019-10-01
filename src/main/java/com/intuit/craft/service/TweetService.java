package com.intuit.craft.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.intuit.craft.exception.RecordNotFoundException;
import com.intuit.craft.model.TweetEntity;
import com.intuit.craft.repository.TweetRepository;
import com.intuit.craft.repository.UserRepository;

/**
 * This service is used to create/search/update tweet service and fetch the list of followers.
 */
@Service
public class TweetService {
     
    @Autowired
    TweetRepository repository;

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    DataQueryService sqlRepo;
    
    //To do: This can be read from properties file but for now as static
    private static final String jsonOutput = "userid,userName,age,followerName,TextMsg,pubishDate";
    private static final String userIdSql = "select user_id from USERS where user_name=";
    private static final String homePgSql = "SELECT e.user_id,e.user_name ,e.age, d.follow_name, r.msg_txt, r.pub_date FROM USERS e JOIN TWEETS r ON e.user_id=r.user_id JOIN FOLLOWS d ON r.follow_id=d.follow_id where e.user_id=";
    
    public List<TweetEntity> getAllTweets()
    {
        List<TweetEntity> TweetList = repository.findAll();
         
        if( TweetList != null && TweetList.size() > 0) {
            return TweetList;
        } else {
            return new ArrayList<TweetEntity>();
        }
    }
    /**
     * This method returns the Login users name from the Ldap login form using securitycontext
     */
    private String getLoginUserNm(){
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		final Authentication authentication = securityContext.getAuthentication();
		String userName = null;
		if (authentication != null)
			userName = authentication.getName();
		return userName;
	}
    
    /**
     * This method is used to access the Homepage of the login user with Offset and max most recent Tweets
     */
    public String getHomePageTwitsWithPaginationLimit(int offset, int limit) throws Exception
    {
    	String loginUser = getLoginUserNm();
    	
    	List<Object> userResp = sqlRepo.getQueryResults(userIdSql + "'" + loginUser + "'", offset, limit);
    	List<Object> homePageResp = null;
    	String arrayToJson = null ;
    	if(userResp != null && userResp.size() > 0) {
    		Integer userId = (Integer) userResp.get(0);
    		homePageResp = sqlRepo.getQueryResults(homePgSql + userId + " ORDER BY r.pub_date DESC", offset, limit);
    		arrayToJson = getJsonFormat(homePageResp);
    	}
    	return arrayToJson;
    } 
   
   /**
    * Converting HomePageTimeline response to Json format from value
    */
 private String getJsonFormat(List<Object> homePageResp) throws Exception {
    	
    	Iterator<Object> itr=homePageResp.iterator();
    	List<Map<String,String>> results = new ArrayList<>();
    	Map<String,String> map = null;
    	while(itr.hasNext()) {
    		Object[] obj = (Object[])itr.next();
    		String[] strArray = jsonOutput.split(",");
    		map = new HashMap<String, String>();
    		
    		for (int i = 0; i < obj.length; i++) {
    			String val = obj[i] == null ? "": String.valueOf(obj[i]);
    			for(String key : strArray) {
    				if(!map.containsKey(key)) { // reading the header as key and value to make Json format
    					map.put(key, val);
    					break;
    				}
    			}
			}
    		results.add(map);
    	}
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	return objectMapper.writeValueAsString(results);
    }
 /**
  * Fetch the specific tweet of the user and the followers
  */
    public TweetEntity getTweetById(Long id) throws RecordNotFoundException
    {
        Optional<TweetEntity> tweet = repository.findById(id);
         
        if(tweet.isPresent()) {
            return tweet.get();
        } else {
            throw new RecordNotFoundException("No Tweet record exist for given id");
        }
    }
    
    /**
     * Persist the new or old tweet records.
     */
    public TweetEntity createOrUpdateTweet(TweetEntity entity) throws RecordNotFoundException
    {
        Optional<TweetEntity> tweet = repository.findById(entity.getTwitId());
         
        if(tweet.isPresent())
        {
        	TweetEntity newEntity = tweet.get();
            newEntity.setUserId(entity.getUserId());
            newEntity.setFollowId(entity.getFollowId());
            newEntity.setMsgTxt(entity.getMsgTxt());
            newEntity.setPublishDate(entity.getPublishDate());
            
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
}