package com.octaspring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handle(Exception ex){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message","Ha ocurrido un error"+ex.getMessage());
		mv.setViewName("error/404");
		return mv;
	}
}
