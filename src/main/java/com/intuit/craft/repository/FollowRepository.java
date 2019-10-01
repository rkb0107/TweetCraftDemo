package com.intuit.craft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.craft.model.FollowEntity;
 
@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
 
}
