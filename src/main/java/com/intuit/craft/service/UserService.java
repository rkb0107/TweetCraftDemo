package com.intuit.craft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.craft.exception.RecordNotFoundException;
import com.intuit.craft.model.UserEntity;
import com.intuit.craft.repository.UserRepository;
 

/**
 * This service is used to create/search/update the Login user details.
 */
@Service
public class UserService {
     
    @Autowired
    UserRepository repository;

    /**
     * Fetching the all the login user list from user tables
     */
    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> userList = repository.findAll();
         
        if( userList != null && userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }
     
    /**
     * Fetching the user login details its id
     */
    public UserEntity getUsersById(Long id) throws RecordNotFoundException
    {
    	Optional<UserEntity> user = repository.findById(id);
        
        if(user.isPresent())
        {
        	return user.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
    
    /**
     * Managing the old and new user login details
     */
    public UserEntity createOrUpdateUser(UserEntity entity) throws RecordNotFoundException
    {
        Optional<UserEntity> user = repository.findById(entity.getUserId());
         
        if(user.isPresent())
        {
        	UserEntity newEntity = user.get();
            newEntity.setUserName(entity.getUserName());
        	newEntity.setAge(entity.getAge());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }
     
}