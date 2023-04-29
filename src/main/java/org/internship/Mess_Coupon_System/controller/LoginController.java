package org.internship.Mess_Coupon_System.controller;

import org.internship.Mess_Coupon_System.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("listStudents", studentService.getAllStudents());
		return "index";
	}
}
