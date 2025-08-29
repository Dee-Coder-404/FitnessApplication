package com.FitnessApplication.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.UserEntity;
import com.FitnessApplication.Model.Workout;

@Repository
public interface WorkoutDao extends JpaRepository<Workout, Integer> {
		
		List<Workout> findByUserEntity(UserEntity user);
}
