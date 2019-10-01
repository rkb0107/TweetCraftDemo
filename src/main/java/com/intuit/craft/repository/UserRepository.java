package com.intuit.craft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.craft.model.UserEntity;
 
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
}
