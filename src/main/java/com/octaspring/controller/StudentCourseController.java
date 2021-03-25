package com.octaspring.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.octaspring.dao.CourseActivityInterface;
import com.octaspring.dao.CourseInterface;
import com.octaspring.dao.PurchaseInterface;
import com.octaspring.entity.Course;

@Controller
public class StudentCourseController {
	
	@Autowired
	CourseInterface courseInterface;
	
	@Autowired
	CourseActivityInterface courseActivityInterface;
	
	@Autowired
	PurchaseInterface purchaseInterface;
	
	@GetMapping("/student_courses")
	public String studentCourse() {
		
		return "student/student_courses";
	}
	
	@GetMapping("/student/cart/add/{idCourse}")
	public String addCart(Model model, @PathVariable("idCourse") int idCourse, HttpSession session) {
		

		boolean resp = courseInterface.verifyUserHasCourse(idCourse);
		Course course = courseInterface.findById(idCourse);
		//System.out.println(resp);
		//opcion 1
		@SuppressWarnings("unchecked")
		List<Course> cart_student = (List<Course>) session.getAttribute("cart1") != null ? (List<Course>) session.getAttribute("cart1") : new ArrayList<Course>();
		//no duplicados...SET
		@SuppressWarnings("unchecked")
		Set<Course> cart_student2 = (HashSet<Course>) session.getAttribute("cart2") != null ? (HashSet<Course>) session.getAttribute("cart2") : new HashSet<Course>();
		
		if(!resp) {
			cart_student.add(course);
			cart_student2.add(course);
			
			session.setAttribute("cart1", cart_student);
			//SET
			session.setAttribute("cart2", cart_student2);
		}
		
		
		return "redirect:/";
	}
	
	@GetMapping("/student/cart")
	public String studentCart(Model model, HttpSession session ) {
		@SuppressWarnings("unchecked")
		List<Course> cart_student = (List<Course>) session.getAttribute("cart1") != null ? (List<Course>) session.getAttribute("cart1") : new ArrayList<Course>();
		//no duplicados...SET
		@SuppressWarnings("unchecked")
		Set<Course> cart_student2 = (HashSet<Course>) session.getAttribute("cart2") != null ? (HashSet<Course>) session.getAttribute("cart2") : new HashSet<Course>();
		model.addAttribute("cart1",cart_student);
		model.addAttribute("cart2",cart_student2);
		return "student/cart";
	}
	
	@PostMapping("/student/payment")
	public String studentCartPayment(Model model, HttpSession session, WebRequest request) {
		@SuppressWarnings("unchecked")
		Set<Course> cart_student2 = (HashSet<Course>) session.getAttribute("cart2") != null ? (HashSet<Course>) session.getAttribute("cart2") : new HashSet<Course>();
		
		//registro compra
		for (Course course2 : cart_student2) {
			//courseInterface.addUserHasCourse(int idCourse) <--crear
			//detalle de la compra "purchase_course"
			//agregar course a student --> Tabla user_has_course
			
			courseInterface.adduserHasCourse(course2.getId());
		}
		purchaseInterface.save(cart_student2);

		return "redirect:/";
	}
	
	@GetMapping("/student/cart/delete/{idCourse}")
	public String deleteCart(@PathVariable("idCourse") int idCourse, HttpSession session, Model model) {
		@SuppressWarnings("unchecked")
		Set<Course> cart_student2 = (HashSet<Course>) session.getAttribute("cart2") != null ? (HashSet<Course>) session.getAttribute("cart2") : new HashSet<Course>();
		
		for(Course course : cart_student2) {
			if(course.getId() == idCourse) {
				cart_student2.remove(course);
			}
		}
		return "redirect:/student/cart";
	}
}
