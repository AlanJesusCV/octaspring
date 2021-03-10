package com.octaspring.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.entity.Subcategory;


@Controller
public class SubcategoryController {

	@Autowired
	private SubcategoryInterface subcategoryInterface;
	
	@Autowired
	private CategoryInterface categoryInterface;
	
	@GetMapping("/subcategory")
	public String subcategory(@ModelAttribute("subcategory") Subcategory subcategory, BindingResult bindingResult, Model model) {
		List<Subcategory> listaSubcategory = subcategoryInterface.findByAll();
		model.addAttribute("listaSubcategory", listaSubcategory);
		model.addAttribute("action","Crear subcategoría");
		model.addAttribute("category", categoryInterface.findByAll());
		return "admin/subcategory";
	}
	
	@GetMapping("/subcategory-get/{id}")
	public String subcategoryGet(@PathVariable("id") Integer id, Subcategory subcategory) {
		subcategoryInterface.findById(id);
		return "redirect:/category";
	}
	
	@PostMapping("/subcategory-create")
	public String subcategoryCreate(
			@Valid Subcategory subcategory,
			BindingResult bindingResult,
			Model model,
			WebRequest request
			) {
		
		long idCategory = Long.parseLong(request.getParameter("category_id"));
		subcategory.setCategory(categoryInterface.findById(idCategory));
		System.out.println(categoryInterface.findById(idCategory));
		/*
		 * if (bindingResult.hasErrors()) { //System.out.println("...Error...");
		 * List<Subcategory> listaSubcategory = subcategoryInterface.findByAll();
		 * model.addAttribute("listaSubcategory",listaSubcategory);
		 * model.addAttribute("action","Crear subcategoria");
		 * model.addAttribute("subcategory", subcategory);
		 * model.addAttribute("category", categoryInterface.findByAll());
		 * model.addAttribute("open", true); return "admin/subcategory"; }
		 */

		if(subcategory.getId() == null) {
			subcategoryInterface.save(subcategory);
		}else {
			subcategoryInterface.update(subcategory);
		}
		
		return "redirect:/subcategory";
	}
	
	@GetMapping("/subcategory-update/{id}")
	public String subcategoryUpdate(@PathVariable("id")int id, @ModelAttribute("subcategory") Subcategory subcategory, Model model) {
		List<Subcategory> listaSubcategory = subcategoryInterface.findByAll();
		
		subcategory = subcategoryInterface.findById(id);
		model.addAttribute("subcategory", subcategory);
		model.addAttribute("listaSubcategory", listaSubcategory);
		model.addAttribute("category", categoryInterface.findByAll());
		model.addAttribute("action","Editar subcategoría");
		
		return "admin/subcategory";
	}
	
	@GetMapping("/subcategory-delete/{id}")
	public String subcategoryDetele(@PathVariable("id") Integer id) {
		subcategoryInterface.delete(id);
		return "redirect:/subcategory";
	}
	
	@GetMapping("/subcategory-updateStatus/{id}")
	public String updateStatus(@PathVariable("id") Integer id, Subcategory subcategory) {
		subcategory = subcategoryInterface.findById(id);
		subcategoryInterface.updateStatus(subcategory);
		return "redirect:/subcategory";
	}
}