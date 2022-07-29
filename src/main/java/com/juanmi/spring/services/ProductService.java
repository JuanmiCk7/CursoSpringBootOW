package com.juanmi.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juanmi.spring.models.Product;
import com.juanmi.spring.models.Purchase;
import com.juanmi.spring.models.User;
import com.juanmi.spring.repositories.ProductRepository;
import com.juanmi.spring.upload.StorageService;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	StorageService storageService;
	
	
	
	public Product insert(Product p) {
		return repository.save(p);
	}
	
	public void delete(long id) {
		if(!findById(id).getImg().isEmpty())
			storageService.delete(findById(id).getImg());
		repository.deleteById(id);
	}
	
	public void delete(Product p) {
		if (!p.getImg().isEmpty())
			storageService.delete(p.getImg());
		repository.delete(p);
	}
	
	public Product edit(Product p) {
		return repository.save(p);
	}
	
	public Product findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public List<Product> findProductByOwner(User u) {
		return repository.findByOwner(u);
	}
	
	public List<Product> productByPurchase(Purchase p) {
		return repository.findByPurchase(p);
	}
	
	public List<Product> productWithoutPurchase() {
		return repository.findByPurchaseIsNull();
	}
	
	public List<Product> searchByQuery(String query) {
		return repository.findByProductNameContainsIgnoreCaseAndPurchaseIsNull(query);
	}
	
	public List<Product> searchByQueryAndUser(String query, User u) {
		return repository.findByProductNameContainsIgnoreCaseAndOwner(query, u);
	}
	
	public List<Product> findAllById(List<Long> id) {
		return repository.findAllById(id);
	}
	
}
