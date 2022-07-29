package com.juanmi.spring.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Purchase {
	
	@Id @GeneratedValue
	private long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	
	@ManyToOne
	private User owner;
	
	public Purchase() {}

	public Purchase(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", purchaseDate=" + purchaseDate + ", owner=" + owner + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, owner, purchaseDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		return id == other.id && Objects.equals(owner, other.owner) && Objects.equals(purchaseDate, other.purchaseDate);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
	
	
	
	
}
