package com.juanmi.spring.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id @GeneratedValue
	private long id;
	
	private String productName;
	
	private float price;
	
	private String img;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Purchase purchase;
	
	public Product() {}

	public Product(String productName, float price, String img, User owner) {
		this.productName = productName;
		this.price = price;
		this.img = img;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, img, owner, price, productName, purchase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id && Objects.equals(img, other.img) && Objects.equals(owner, other.owner)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(productName, other.productName) && Objects.equals(purchase, other.purchase);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", img=" + img + ", owner="
				+ owner + ", purchase=" + purchase + "]";
	}
	
	
	
}
