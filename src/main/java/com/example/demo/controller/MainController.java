package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.mapping.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Programmer;
import com.example.demo.repository.ProgrammerRepo;

//import ch.qos.logback.core.model.Model;

@Controller
@ControllerAdvice
public class MainController {
	
	@Autowired
	ProgrammerRepo pr;
	
	//@RequestMapping("/home")
	@GetMapping("/home")
	public String homePage() {
		return "HomePage.html";
	}
		
	//third way to send data to frontend
	//@RequestMapping("/addProgrammer")
	//@RequestMapping(value="/addProgrammer", method=RequestMethod.POST)
	@PostMapping("/addProgrammer")
	public String addProgrammer(@ModelAttribute("p") Programmer programmer) {
		System.out.println("Id : "+programmer.getpId()+"  Name : "+programmer.getpName()+"  language : "+programmer.getpLang());
		pr.save(programmer);
		//return "ProgrammerInfo.html";
		return "redirect:/home";
	}
	
	@PostMapping("/findById")
	public String findById(@RequestParam int pId, Model m) {
		Programmer p = pr.getOne(pId);
		m.addAttribute("p", p);
		
		return "ProgrammerInfo.html";
	}
	
	@PostMapping("/findByLang")
	public String findByLang(@RequestParam String pLang, Model m) {
		
		List<Programmer> p = pr.findBypLang(pLang);
		
		m.addAttribute("programmers", p);
		
		return "AllProgrammer.html";
	}
	
	@PostMapping("/findByName")
	public String findByName(@RequestParam String pName, Model m) {
		
		List<Programmer> p = pr.findBypName(pName);
		
		m.addAttribute("programmers", p);
		
		return "AllProgrammer.html";
	}
	
	@PostMapping("/updateProgrammer")
	public String updateProgrammer(@ModelAttribute Programmer programmer) {
		Programmer p = pr.getOne(programmer.getpId());
		if(!programmer.getpName().isEmpty() && programmer.getpName()!=null)
		p.setpName(programmer.getpName());
		
		if(!programmer.getpLang().isEmpty() && programmer.getpLang()!=null)
		p.setpLang(programmer.getpLang());
		
		pr.save(p);
		return "redirect:/home";
	}
	
	@GetMapping("/deleteProgrammer")
	public String deleteProgrammer(@RequestParam int pId, Model m) {
		pr.deleteById(pId);
		return "redirect:/home";
	}
	
	
	/*
	 	//second way to send data to frontend

	@RequestMapping("/addProgrammer")
	public ModelAndView addProgrammer(String pId, @RequestParam String pName, String pLang) {
		System.out.println("Id : "+pId+"  Name : "+pName+"  language : "+pLang);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ProgrammerInfo.html");
		mv.addObject("pName", pName);
		mv.addObject("pId", pId);
		mv.addObject("pLang", pLang);
		
		
		return mv;
	}
	
	  */
	
	//first way to send data to frontend
	/*
	@RequestMapping("/addProgrammer")
	public String addProgrammer(@RequestParam String pId, @RequestParam String pName, @RequestParam String pLang, Model model) {
		System.out.println("Id : "+pId+"  Name : "+pName+"  language : "+pLang);
		
		model.addAttribute("pName", pName);
		model.addAttribute("pId", pId);
		model.addAttribute("pLang", pLang);

		
		return "ProgrammerInfo";
	}
	 */
	
	@ModelAttribute //now in every page this message is available
	// if we have multiple controller class then by using @controllerAdvice we can 
	// declare this @ModelAttribute globally
	public void welcomeMessage(Model model) {
		
		model.addAttribute("message", "Welocme everyone to my Web Application");
	}
	@RequestMapping("/raju")
	public String rajuPage() {
		return "RajuPage.html";
	}
	
	@RequestMapping("/shakil")
	public String shakilPage() {
		return "ShakilPage.html";
	}
	
	@GetMapping("/allProgrammer")
	public String home(Model m) {
		
		List<Programmer> p= new ArrayList<Programmer>();
		p.add(new Programmer(101, "Nahid", "Java"));
		p.add(new Programmer(101, "Raju", "Java"));
		p.add(new Programmer(101, "Shakil", "Java"));
		m.addAttribute("programmers", p);
		
		return "AllProgrammer.html";
	}
	
	
	/*
	 	@GetMapping("/allProgrammer")
	public String allProgrammer(Model m) {
		
		return "AllProgrammer.html";
	}
	
	 */
}
