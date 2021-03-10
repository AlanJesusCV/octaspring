package com.octaspring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.dao.LangInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Lang;
import com.octaspring.entity.UserPerson;
@Controller

public class CategoryController {
	@Autowired
	private CategoryInterface categoryInterface;
	
	@GetMapping("/category")
	public String category(@ModelAttribute("category") Category category, BindingResult result,Model model) {
		
		List<Category> listaCTRL = categoryInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaTL",listaCTRL);
		model.addAttribute("action", "Registrar");
		System.out.print(listaCTRL);
		return "admin/category";
	}
	@PostMapping("/category-create")
	public String categoryCreate(Model model ,@Valid Category category,BindingResult result, @RequestParam("file") CommonsMultipartFile file, HttpSession session) throws IOException {	
		if(result.hasErrors()) {
			System.out.println("errores");
			List<Category> listaCTRL = categoryInterface.findByAll();
			//Thymeleaf TL
			model.addAttribute("listaTL",listaCTRL);
			model.addAttribute("action", "Registrar");
			model.addAttribute("category",category);
			return "admin/category";
		}
		String path = session.getServletContext().getRealPath("/");
		System.out.println(path);
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path pathUpload = Paths.get(path+"/WEB-INF/uploads/category"+file.getOriginalFilename());
			Files.write(pathUpload, bytes);
			category.setImage(file.getOriginalFilename());
		}else {
			category.setImage("default.png");
		}
		
		if(category.getId() == null) {
			categoryInterface.save(category);
		}else{
			categoryInterface.update(category);
		}
		
		return "redirect:/category";

	}
	@GetMapping("/category-delete/{id}")
	public String categoryDelete(@PathVariable("id") Integer id) {
		categoryInterface.delete(id);
		
		return "redirect:/category";
		
	}

	@GetMapping("/category-status/{id}/{status}")
	public String categoryStatus(@PathVariable("id") Integer id,@PathVariable("status") int status) {
		///status
		
		int newStatus=(status == 2) ? 1 : 2;
		categoryInterface.deleteLogical(id,newStatus);
		
		return "redirect:/category";
		
	}
	@GetMapping("/category-update/{id}")
	public String categoryUpdate(@PathVariable("id") Integer id,@ModelAttribute("category") Category category, BindingResult result,Model model) {
		category = categoryInterface.findById(id);	
		List<Category> listaCTRL = categoryInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaTL",listaCTRL);
		model.addAttribute("action", "Actualizar");
		model.addAttribute("category",category);
		return "admin/category";
		
	}
	
}
	

