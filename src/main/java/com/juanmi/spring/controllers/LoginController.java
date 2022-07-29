package com.juanmi.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.juanmi.spring.models.User;
import com.juanmi.spring.services.UserService;
import com.juanmi.spring.upload.StorageService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}
	
	@GetMapping("/auth/login") 
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute User u, @RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()) {
			String img = storageService.store(file);
			u.setAvatar(MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", img).toUriString());
		}
		userService.insert(u);
		return "redirect:/auth/login";
	}
	
}
