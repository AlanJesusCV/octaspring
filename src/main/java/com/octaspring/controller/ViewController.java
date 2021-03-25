package com.octaspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.octaspring.dao.CourseActivityInterface;
import com.octaspring.dao.CourseInterface;
import com.octaspring.dao.LangInterface;
import com.octaspring.dao.RoleInterface;
import com.octaspring.entity.Course;
import com.octaspring.entity.CourseActivity;
import com.octaspring.entity.Lang;
import com.octaspring.entity.UserPerson;

@Controller
public class ViewController {
	@Autowired
	private RoleInterface roleInterface;
	
	@Autowired
	private CourseInterface courseInterface;
	
	@Autowired
	private CourseActivityInterface courseActivityInterface;
	
	@GetMapping("/")
	public String index(@ModelAttribute("course") Course course, Model model) {
		List<Course> listaCourse = courseInterface.findCoursesByStatus(2);
		model.addAttribute("listaCourse", listaCourse);
		return "index";
	}
	
	@GetMapping("/fbc/{category}")
	public String CourseFiltered(@PathVariable("category") int category, Model model) {
		List<Course> listaCourse = courseInterface.findCoursesByCategory(category);
		model.addAttribute("listaCourse", listaCourse);
		return "index";
		//return "courses/coursesbc";
	}
	
	@GetMapping("/see-course/{id}")
	public String CourseDetail(@PathVariable("id")int id, @ModelAttribute("course") Course course, Model model) {
		course = courseInterface.findById(id);
		List<CourseActivity> listaCourseActivity = courseActivityInterface.findByc(id);
		model.addAttribute("course", course);
		model.addAttribute("listaCourseActivity",listaCourseActivity);
		return "/verCursos";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("userPerson") UserPerson userPerson, BindingResult result, Model model) {
		model.addAttribute("roles",roleInterface.findRolesNotAdmin());	
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String auth() {
		return "login";
	}
	
	@PostMapping("/search/courses")
	public String seachCourses(Model model, WebRequest request) {
		String searchword = request.getParameter("searchword");
		
		model.addAttribute("listaCourse", courseInterface.findAllBySearch(searchword));
		
		return "index";
	}
	
}
