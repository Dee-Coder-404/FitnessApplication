package com.FitnessApplication.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.FitnessApplication.Binding.LoginUser;
import com.FitnessApplication.Binding.UserBind;
import com.FitnessApplication.Dao.UserDao;
import com.FitnessApplication.Model.UserEntity;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userdao;
	
	@GetMapping("/show")
	public String showRegistrationForm(Model model) {
		
		model.addAttribute("userbind", new UserBind());
		return "register";
	}
	
	@PostMapping("/register")
	public String Register(UserBind userbind,Model model) {
		
		UserEntity user=new UserEntity();
		BeanUtils.copyProperties(userbind, user);
		UserEntity save=userdao.save(user);
		if(save!=null) {
			model.addAttribute("loginRequest", new LoginUser());
			return "login";
		}
		else {
			model.addAttribute("msg", "Registration Unsucessfull");
			return "register";
		}
		
	}
	
	@GetMapping("/login")
	public String ShowLogin(Model model) {
		model.addAttribute("loginRequest", new LoginUser());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model,HttpSession session,LoginUser username) {
			
		String user=username.getUsername();
		String pswd=username.getPassword();
		
			UserEntity find=userdao.findByUsername(user);
			
			if(find!=null && find.getPassword().equals(pswd)) {
				session.setAttribute("username", user);
				return "home";
			}
			else {
				model.addAttribute("error", "Invalid Id or Password");
				model.addAttribute("loginRequest", new LoginUser());
				return "login";
			}
	
	}
	
	
	
}
