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

import com.octaspring.dao.CourseActivityInterface;
import com.octaspring.dao.CourseInterface;
import com.octaspring.entity.Course;
import com.octaspring.entity.CourseActivity;

@Controller
public class CourseActivityController {
	@Autowired
	CourseActivityInterface courseActivityInterface;
	
	@Autowired
	CourseInterface courseInterface;
	  
	  @PostMapping("/activity-create")
		public String courseActivityCreate(CourseActivity courseActivity, 
				WebRequest request, 
				HttpSession session,
				BindingResult bindingResult,
				@RequestParam("course-file") CommonsMultipartFile file,
				@RequestParam("course-video") CommonsMultipartFile video) throws IOException {
			
			long idCourse = Long.parseLong(request.getParameter("course_id"));
			courseActivity.setCourse(courseInterface.findById(idCourse));
			
				String path = session.getServletContext().getRealPath("/");
				if(!file.isEmpty()) {
					byte[] bytes = file.getBytes();
					Path pathUpload = Paths.get(path+"/WEB-INF/uploads/course"+file.getOriginalFilename());
					Files.write(pathUpload, bytes);
					courseActivity.setFile(file.getOriginalFilename());
				} else {
					courseActivity.setFile("noFileAdded");
				}
				
				if(!video.isEmpty()) {
					byte[] bytes = video.getBytes();
					Path pathUpload = Paths.get(path+"/WEB-INF/uploads/course"+video.getOriginalFilename());
					Files.write(pathUpload, bytes);
					courseActivity.setVideo(video.getOriginalFilename());
				} else {
					courseActivity.setVideo("noVideoAdded");
				}
	
			
			if(courseActivity.getId() == null) {
				courseActivityInterface.save(courseActivity);
			}else {
				courseActivityInterface.update(courseActivity);
			}
			
			return "redirect:/course-details/"+idCourse;
		}
	  
	  @GetMapping("/activity-update/{cid}/{caid}")
		public String CourseActivityUpdate(@PathVariable("caid") int caid, @PathVariable("cid") int cid, @ModelAttribute("courseActivity") CourseActivity courseActivity, Model model) {
			
			courseActivity = courseActivityInterface.findById(caid);
			Course course = courseInterface.findById(cid);
			List<CourseActivity> listaCourseActivity = courseActivityInterface.findByc(cid);
			
			model.addAttribute("courseActivity", courseActivity);
			model.addAttribute("listaCourseActivity",listaCourseActivity);
			model.addAttribute("course", course);
			model.addAttribute("action", "Actualizar");
			
			return "teacher/courseActivity";
		}
		
		@GetMapping("/activity-delete/{cid}/{caid}")
		public String courseActivityDetele(@PathVariable("caid") int caid, @PathVariable("cid") int cid) {
			courseActivityInterface.delete(caid);
			return "redirect:/course-details/"+cid;
		}
}
