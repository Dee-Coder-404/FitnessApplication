package com.FitnessApplication.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
		
	public UserEntity findByUsername(String s);
}
