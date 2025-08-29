package com.FitnessApplication.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FitnessApplication.Binding.WorkoutBind;
import com.FitnessApplication.Dao.UserDao;
import com.FitnessApplication.Dao.WorkoutDao;
import com.FitnessApplication.Model.UserEntity;
import com.FitnessApplication.Model.Workout;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/workout")
public class WorkoutController {
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private WorkoutDao workoutDao;
	
	@GetMapping("/create")
	public String ShowWorkout(Model model) {
		model.addAttribute("workout", new WorkoutBind());
		return "createworkout";
	}
	
	@PostMapping("/save")
	public String createworkout(Model model,WorkoutBind workoutBind,HttpSession session) {
		
		UserEntity user=userdao.findByUsername((String)session.getAttribute("username"));
		
		if(user==null) {
			model.addAttribute("message", "Please Login to create Workout");
			return "redirect:/login";
		}

		
		Workout workout=new Workout();
		BeanUtils.copyProperties(workoutBind, workout);
		workout.setUserEntity(user);
		Workout work=workoutDao.save(workout);

		
		if(work!=null) {
			model.addAttribute("message", "Created Workout");
			model.addAttribute("workout", new WorkoutBind());
		}
		else {
			model.addAttribute("message", "Unsuccessful in Creating Workout");
			model.addAttribute("workout", new WorkoutBind());
		}
		return "home";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/view")
	public String viewWorkout(HttpSession session,Model model) {
		UserEntity user=userdao.findByUsername((String)session.getAttribute("username"));
		List<Workout> workouts=workoutDao.findByUserEntity(user);
		model.addAttribute("workouts", workouts);
		return "displayworkout";
	}
}
