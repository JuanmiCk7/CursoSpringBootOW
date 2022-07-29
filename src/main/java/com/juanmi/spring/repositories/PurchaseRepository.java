package com.juanmi.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juanmi.spring.models.Purchase;
import com.juanmi.spring.models.User;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
	
	
	List<Purchase> findByOwner(User owner);
	
}
