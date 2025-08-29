package com.FitnessApplication.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FitnessApplication.Binding.GoalBind;
import com.FitnessApplication.Dao.GoalDao;
import com.FitnessApplication.Dao.UserDao;
import com.FitnessApplication.Model.Goal;
import com.FitnessApplication.Model.UserEntity;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/goals")
public class GoalController {
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private GoalDao goalDao;
	
	@GetMapping("/create")
	public String showGoal(Model model) {
		model.addAttribute("goal", new GoalBind());
		return "creategoal";
	}
	
	@PostMapping("/save")
	public String creategoal(Model model,HttpSession session,GoalBind goalBind) {
		
		UserEntity user=userdao.findByUsername((String)session.getAttribute("username"));
		if(user==null) {
			model.addAttribute("error", "Please Login !!!!!");
			return "redirect:/login";
		}
		
		Goal goal=new Goal();
		BeanUtils.copyProperties(goalBind, goal);
		
		goal.setUserEntity(user);
		
		Goal save=goalDao.save(goal);
		
		if(save!=null) {
			model.addAttribute("message", "Goal Created");
			model.addAttribute("goal", new GoalBind());
		}
		else {
			model.addAttribute("message", "Goal Not Created");
			model.addAttribute("goal", new GoalBind());
		}
		
		return "home";
	}
	
	@GetMapping("/view")
	public String displayGoals(HttpSession session,Model model) {
		UserEntity user=userdao.findByUsername((String)session.getAttribute("username"));
		List<Goal> goals=goalDao.findByUserEntity(user);
		model.addAttribute("goals", goals);
		return "displaygoals";
	}
}
