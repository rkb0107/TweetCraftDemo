package com.intuit.craft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.craft.model.TweetEntity;
 
@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
}
