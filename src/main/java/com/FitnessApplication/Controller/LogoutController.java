package com.FitnessApplication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.FitnessApplication.Binding.LoginUser;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
		
	@GetMapping("/logout")
	public String logout(Model model,HttpSession session) {
		
		session.invalidate();
		model.addAttribute("message", "Logout Successful");
		model.addAttribute("loginRequest", new LoginUser());
		return "login";
	}
}
