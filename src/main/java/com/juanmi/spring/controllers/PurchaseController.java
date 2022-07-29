package com.juanmi.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juanmi.spring.models.Product;
import com.juanmi.spring.models.Purchase;
import com.juanmi.spring.models.User;
import com.juanmi.spring.services.ProductService;
import com.juanmi.spring.services.PurchaseService;
import com.juanmi.spring.services.UserService;

@Controller
@RequestMapping("/app")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	HttpSession session;
	
	private User user;
	
	@ModelAttribute("cart")
	public List<Product> productsInCart() {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		return (content == null) ? null : productService.findAllById(content);
	}
	
	@ModelAttribute("all_cart")
	public Double allCart() {
		List<Product> productInCart = productsInCart();
		if(productInCart != null) {
			return productInCart.stream().mapToDouble(p -> p.getPrice()).sum();
		}
		return 0.0;
	}
	
	@ModelAttribute("my_purchases")
	public List<Purchase> myPurchases() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return purchaseService.findByOwner(user);
	}
	
	@GetMapping("/cart")
	public String showCart(Model model) {
		return "app/purchase/cart";
	}
	
	@GetMapping("/cart/add/{id}")
	public String addCart(Model model, @PathVariable Long id) {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		if(content == null)
			content = new ArrayList<>();
		if(!content.contains(id))
			content.add(id);
		session.setAttribute("cart", content);
		return "redirect:/app/cart";
	}
	
	@GetMapping("/cart/delete/{id}")
	public String deleteCart(Model model, @PathVariable Long id) {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		if(content == null)
			return "redirect:/public";
		content.remove(id);
		if(content.isEmpty())
			session.removeAttribute("cart");
		else
			session.setAttribute("cart", content);
		return "redirect:/app/cart";
	}
	
	@GetMapping("/cart/end")
	public String checkout() {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		if(content == null)
			return "redirect:/public";
		
		List<Product> products = productsInCart();
		
		Purchase purchase = purchaseService.insert(new Purchase(), user);
		
		products.forEach(p -> purchaseService.addProductToPurchase(p, purchase));
		session.removeAttribute("cart");
		
		return "redirect:/app/purchase/invoice/"+purchase.getId();
		
		
	}
	
	@GetMapping("/purchase/invoice/{id}")
	public String invoice(Model model, @PathVariable Long id) {
		Purchase purchase = purchaseService.findById(id);
		List<Product> products = productService.productByPurchase(purchase);
		model.addAttribute("products", products);
		model.addAttribute("purchase", purchase);
		model.addAttribute("all_cart", products.stream().mapToDouble(p -> p.getPrice()).sum());
		return "/app/purchase/invoice";
	}
	
	@GetMapping("/my_purchases")
	public String showMyPurchases(Model model) {
		return "/app/purchase/list";
	}
	
}
