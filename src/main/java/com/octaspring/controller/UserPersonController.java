package com.octaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.UserPerson;

@Controller
public class UserPersonController {
	
	@Autowired
	private UserPersonInterface userpersonInterface;

	@PostMapping("/user-create")	
	public String userpersonaCreate(UserPerson userPerson, @ModelAttribute("selectRole") int role) {
		System.out.println(userPerson.toString());
		userpersonInterface.save(userPerson,role);
		return "redirect:/register";
	}
}
