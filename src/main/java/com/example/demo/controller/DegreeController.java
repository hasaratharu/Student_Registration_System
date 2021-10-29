package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Degree;
import com.example.demo.service.DegreeService;

@Controller
public class DegreeController {
	@Autowired
	private DegreeService degreeService;
	
	@GetMapping("/viewDegree")
	public String viewDegreeList(Model model) {
		model.addAttribute("listDegree", degreeService.getAllDegree());
		return "viewDegree";
	}
	
	@GetMapping("/showNewDegreeForm")
	public String showNewDegreeForm(Model model) {
		Degree degree = new Degree();
		model.addAttribute("degree", degree);
		return "addNewDegree";
	}
	
	@PostMapping("/saveDegree")
	public String saveDegree(@ModelAttribute("degree") Degree degree) {
		degreeService.saveDegree(degree);
		return "redirect:/viewDegree";
	}
	
	@GetMapping("/showFormForUpdateDegree/{id}")
	public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
		Degree degree = degreeService.getDegreeById(id);
		model.addAttribute("degree", degree);
		return "updateDegree";	
	}
	
	@GetMapping("/deleteDegree/{id}")
	public String deleteDegree(@PathVariable( value = "id") long id) {
		this.degreeService.deleteDegreeById(id);
		return "redirect:/viewDegree";
		
	}
}
