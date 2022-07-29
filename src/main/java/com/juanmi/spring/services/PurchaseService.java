package com.juanmi.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanmi.spring.models.Product;
import com.juanmi.spring.models.Purchase;
import com.juanmi.spring.models.User;
import com.juanmi.spring.repositories.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	PurchaseRepository repository;
	
	@Autowired
	ProductService productService;
	
	public Purchase insert(Purchase p, User u) {
		p.setOwner(u);
		return repository.save(p);
	}
	
	public Purchase insert(Purchase p) {
		return repository.save(p);
	}
	
	public Product addProductToPurchase(Product prod, Purchase p) {
		prod.setPurchase(p);
		return productService.edit(prod);
	}
	
	public Purchase findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Purchase> findByOwner(User u) {
		return repository.findByOwner(u);
	}
	
	public List<Purchase> findAll() {
		return repository.findAll();
	}
	
}
