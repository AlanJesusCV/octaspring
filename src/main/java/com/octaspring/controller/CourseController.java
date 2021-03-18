package com.octaspring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.dao.CourseActivityInterface;
import com.octaspring.dao.CourseInterface;
import com.octaspring.dao.LangInterface;
import com.octaspring.dao.LevelInterface;
import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.dao.UserPersonInterface;
import com.octaspring.entity.Course;
import com.octaspring.entity.CourseActivity;
import com.octaspring.entity.Subcategory;
import com.octaspring.entity.UserPerson;

@Controller
public class CourseController {
	@Autowired
	private CourseInterface courseInterface;
	
	@Autowired
	private CategoryInterface categoryInterface;
	
	@Autowired
	private SubcategoryInterface subcategoryInterface;
	
	@Autowired
	private LevelInterface levelInterface;
	
	@Autowired
	private LangInterface langInterface;
	
	@Autowired
	private UserPersonInterface userpersonInterface;
	
	@Autowired
	private CourseActivityInterface courseActivityInterface;
	
	@GetMapping("/course")
	public String course(@ModelAttribute("course") Course course, Model model) {
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    String email = userDetail.getUsername();
		long id = userpersonInterface.findByEmail(email).getId();
		
		List<Course> listaCourse = courseInterface.findAllByOwnwer((int)id);
		model.addAttribute("listaCourse", listaCourse);
		model.addAttribute("action","Crear curso");
		model.addAttribute("category", categoryInterface.findByAll());
		model.addAttribute("lang", langInterface.findByAll());
		model.addAttribute("level", levelInterface.findByAll());
		model.addAttribute("owner", userpersonInterface.findByAll());
		model.addAttribute("subcategory", subcategoryInterface.findByAll());

	
		return "teacher/course";
	}
	
	@PostMapping("/course-create")
	public String courseCreate(Course course,
			BindingResult bindingResult,
			Model model,
			WebRequest request,
			@RequestParam("image") CommonsMultipartFile image, 
			HttpSession session) throws IOException {
		
		long idCategory = Long.parseLong(request.getParameter("category_id"));
		course.setCategory(categoryInterface.findById(idCategory));
		
		long idSubcategory = Long.parseLong(request.getParameter("subcategory_id"));
		course.setSubcategory(subcategoryInterface.findById(idSubcategory));
		
		long idLang = Long.parseLong(request.getParameter("lang_id"));
		course.setLang(langInterface.findById(idLang));
		
		long idLevel = Long.parseLong(request.getParameter("level_id"));
		course.setLevel(levelInterface.findById(idLevel));
		
		String path = session.getServletContext().getRealPath("/");
		System.out.println(path);
		if(!image.isEmpty()) {
			byte[] bytes = image.getBytes();
			Path pathUpload = Paths.get(path+"/WEB-INF/uploads/course"+image.getOriginalFilename());
			Files.write(pathUpload, bytes);
			course.setImage(image.getOriginalFilename());
		}else {
			course.setImage("default.png");
		}
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    String email = userDetail.getUsername();
		course.setOwner(userpersonInterface.findByEmail(email));
		
		if(course.getId() == null) {
			courseInterface.save(course);
		}else {
			courseInterface.update(course);
		}
		return "redirect:/course";
	}
	
	@GetMapping("/course-update/{id}")
	public String CourseUpdate(@PathVariable("id")int id, @ModelAttribute("course") Course course, Model model) {
		List<Course> listaCourse = courseInterface.findByAll();
		
		course = courseInterface.findById(id);
		model.addAttribute("course", course);
		model.addAttribute("listaCourse", listaCourse);
		model.addAttribute("action","Editar curso");
		model.addAttribute("category", categoryInterface.findByAll());
		model.addAttribute("lang", langInterface.findByAll());
		model.addAttribute("level", levelInterface.findByAll());
		model.addAttribute("owner", userpersonInterface.findByAll());
		model.addAttribute("subcategory", subcategoryInterface.findByAll());
		
		return "teacher/course";
	}
	
	@GetMapping("/course-delete/{id}")
	public String courseDetele(@PathVariable("id") Integer id) {
		courseInterface.delete(id);
		return "redirect:/course";
	}
	

	
	@GetMapping("/course-details/{courseid}")
	public String CourseDetail(
			@PathVariable("courseid")int courseid, 
			@ModelAttribute("courseActivity") CourseActivity courseActivity,
			Model model) {
		
		Course course = courseInterface.findById(courseid);
		List<CourseActivity> listaCourseActivity = courseActivityInterface.findByc(courseid);
		
		model.addAttribute("course", course);
		model.addAttribute("listaCourseActivity",listaCourseActivity);
		
		model.addAttribute("action", "Registrar");
		
		return "teacher/courseActivity";
	}
	
	@GetMapping("/course-publicar/{id}/{status}")
	public String CourseStatus(@PathVariable("id") Integer id, @PathVariable("status") int status) {
		courseInterface.updateStatus(status, id);
		return "redirect:/course";
	}
}
