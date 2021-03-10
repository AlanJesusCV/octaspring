package com.octaspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.entity.Category;

@ControllerAdvice
public class AdvisorController {
	@Autowired
	private CategoryInterface categoryInterface;
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handle(Exception ex) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Ocurrio un error");
		mv.setViewName("error/404");
		return mv;
		
	}
	
	@ModelAttribute("menuCategory")
	public List<Category> menu(){
		return categoryInterface.findByAll();
	}
}
