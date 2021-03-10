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

import com.octaspring.dao.LangInterface;
import com.octaspring.dao.LevelInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Level;

@Controller
public class LevelController {

	@Autowired
	private LevelInterface levelInterface;
	

	@GetMapping("/level")
	public String level(@ModelAttribute("level") Level level, BindingResult result, Model model) {
		List<Level> listaLevel = levelInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaLevel",listaLevel);
		return "admin/level";
	}
	@PostMapping("/level-create")
	public String levelCreate(Level level) {	
		if(level.getId()==null) {
			levelInterface.save(level);
		}else {
			levelInterface.update(level);
		}
		return "redirect:/level";

	}
	@GetMapping("/level-delete/{id}")
	public String levelDelete(@PathVariable("id") Integer id) {
		levelInterface.delete(id);
		
		return "redirect:/level";
		
	}

	@GetMapping("/level-update/{id}")
	public String categoryUpdate(@PathVariable("id") Integer id,@ModelAttribute("category") Level level, BindingResult result,Model model) {
		level = levelInterface.findById(id);	
		List<Level> listaCTRL = levelInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaRole","listaRole");
		model.addAttribute("action", "Actualizar");
		model.addAttribute("level",level);
		return "admin/level";
		
	}
}
