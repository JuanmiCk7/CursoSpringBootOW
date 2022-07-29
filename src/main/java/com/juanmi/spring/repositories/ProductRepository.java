package com.juanmi.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juanmi.spring.models.Product;
import com.juanmi.spring.models.Purchase;
import com.juanmi.spring.models.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByOwner(User owner);
	List<Product> findByPurchase(Purchase purchase);
	List<Product> findByPurchaseIsNull();
	List<Product> findByProductNameContainsIgnoreCaseAndPurchaseIsNull(String name);
	List<Product> findByProductNameContainsIgnoreCaseAndOwner(String name, User owner);
	
}
