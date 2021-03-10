package com.octaspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.octaspring.dao.LangInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Lang;

@Controller
public class LangController {

	
	@Autowired
	private LangInterface langInterface;
	

	@GetMapping("/lang")
	public String lang(@ModelAttribute("lang") Lang lang, BindingResult result, Model model) {
		List<Lang> listaCTRL = langInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaTL",listaCTRL);
		return "admin/lang";
	}
	
	@PostMapping("/lang-create")
	public String langCreate(@Validated Lang lang) {	
		if(lang.getId()==null) {
			langInterface.save(lang);
		}else {
			langInterface.update(lang);
		}
		return "redirect:/lang";
	}
	@GetMapping("/lang-delete/{id}")
	public String langDelete(@PathVariable("id") Integer id) {
		langInterface.delete(id);
		
		return "redirect:/lang";
		
	}
	@GetMapping("/lang-update/{id}")
	public String langUpdate(@PathVariable("id") Integer id,@ModelAttribute("lang") Lang lang, BindingResult result,Model model) {
		lang = langInterface.findById(id);	
		List<Lang> listaCTRL = langInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaTL",listaCTRL);
		model.addAttribute("action", "Actualizar");
		model.addAttribute("lang",lang);
		return "admin/lang";
		
	}
	
}
