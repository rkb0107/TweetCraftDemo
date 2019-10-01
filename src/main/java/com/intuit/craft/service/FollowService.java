package com.intuit.craft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.craft.exception.RecordNotFoundException;
import com.intuit.craft.model.FollowEntity;
import com.intuit.craft.repository.FollowRepository;
 
/**
 * This service is used to create/search/update follower service.
 * Follower information will be created for tacking.
 */
@Service
public class FollowService {
     
    @Autowired
    FollowRepository repository;

    public List<FollowEntity> getAllFollows()
    {
        List<FollowEntity> followList = repository.findAll();
         
        if( followList != null && followList.size() > 0) {
            return followList;
        } else {
            return new ArrayList<FollowEntity>();
        }
    }
     
    public FollowEntity getFollowById(Long id) throws RecordNotFoundException
    {
        Optional<FollowEntity> follow = repository.findById(id);
         
        if(follow.isPresent()) {
            return follow.get();
        } else {
            throw new RecordNotFoundException("No Follow record exist for given id");
        }
    }
    /**
     * Managing old or new Follower records.
     */
     
    public FollowEntity createOrUpdateFollow(FollowEntity entity) throws RecordNotFoundException
    {
        Optional<FollowEntity> follow = repository.findById(entity.getFollowId());
         
        if(follow.isPresent())
        {
        	FollowEntity newEntity = follow.get();
            newEntity.setFollowName(entity.getFollowName());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
}