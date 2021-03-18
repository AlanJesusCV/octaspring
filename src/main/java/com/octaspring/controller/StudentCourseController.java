package com.octaspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentCourseController {

	@GetMapping("/student_courses")
	public String studentCourse() {
		
		return "student/student_courses";
	}
}
