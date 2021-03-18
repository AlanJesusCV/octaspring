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

import com.octaspring.dao.RoleInterface;
import com.octaspring.entity.Category;
import com.octaspring.entity.Role;

@Controller
public class RoleController {
	
	@Autowired
	private RoleInterface roleInterface;
	
	@GetMapping("/role")
	public String role(@ModelAttribute("role") Role role, BindingResult result,Model model) {
		
		List<Role> listaRole = roleInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaRole",listaRole);
		model.addAttribute("action", "Registrar");
		System.out.print(listaRole);
		return "admin/role";
	}
	@PostMapping("/role-create")
	public String roleCreate(Role role) {	
		if(role.getId()==null) {
			roleInterface.save(role);
		}else {
			roleInterface.update(role);
		}
		return "redirect:/role";

	}
	@GetMapping("/role-delete/{id}")
	public String roleDelete(@PathVariable("id") Integer id) {
		roleInterface.delete(id);
		
		return "redirect:/role";
		
	}
	@GetMapping("/role-status/{id}/{status}")
	public String roleStatus(@PathVariable("id") Integer id,@PathVariable("status") int status) {
		///status
		
		int newStatus=(status == 2) ? 1 : 2;
		roleInterface.deleteLogical(id,newStatus);
		
		return "redirect:/role";
		
	}
	@GetMapping("/role-update/{id}")
	public String roleUpdate(@PathVariable("id") Integer id,@ModelAttribute("role") Role role, BindingResult result,Model model) {
		role = roleInterface.findById(id);	
		List<Role> listaCTRL = roleInterface.findByAll();
		//Thymeleaf TL
		model.addAttribute("listaRole",listaCTRL);
		model.addAttribute("action", "Actualizar");
		model.addAttribute("role",role);
		return "admin/role";
		
	}
}
