package com.juanmi.spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.juanmi.spring.models.Product;
import com.juanmi.spring.models.User;
import com.juanmi.spring.services.ProductService;
import com.juanmi.spring.services.UserService;
import com.juanmi.spring.upload.StorageService;

@Controller
@RequestMapping("/app")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;
	
	private User user;
	
	@ModelAttribute("my_products")
	public List<Product> myProducts() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return productService.findProductByOwner(user);
	}
	
	@GetMapping("/my_products")
	public String list(Model model, @RequestParam(name = "q", required = false) String query) {
		if(query != null)
			model.addAttribute("my_products", productService.searchByQuery(query));
		return "app/product/list";
	}
	
	@GetMapping("/my_products/{id}/delete")
	public String delete(@PathVariable Long id) {
		Product p = productService.findById(id);
		if(p.getPurchase() == null)
			productService.delete(id);
		return "redirect:/app/my_products";
	}
	
	@GetMapping("/product/new")
	public String newProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "app/product/form";
	}
	
	@PostMapping("/product/new/submit")
	public String newProductSubmit(@ModelAttribute Product product,
			@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()) {
			String img = storageService.store(file);
			product.setImg(MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", img).toUriString());
		}
		product.setOwner(user);
		productService.insert(product);
		return "redirect:/app/my_products";
	}
	
	
	
	
	
}
