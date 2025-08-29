package com.FitnessApplication.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.Goal;
import com.FitnessApplication.Model.UserEntity;

@Repository
public interface GoalDao extends JpaRepository<Goal, Integer> {

	List<Goal> findByUserEntity(UserEntity user);
}
