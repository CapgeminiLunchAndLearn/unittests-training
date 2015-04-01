package com.programmaniaks.training.unittest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private int quantity;
	private double price;
	private String description;
	
	public Article()  {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.name, this.quantity, this.price, this.description);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass())
			return false;
		final Article other = (Article) obj;
		return Objects.equal(this.id, other.id)
				&& Objects.equal(this.name, other.name)
				&& Objects.equal(this.quantity, other.quantity)
				&& Objects.equal(this.price, other.price)
				&& Objects.equal(this.description, other.description);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.addValue(this.id)
				.addValue(this.name)
				.addValue(this.quantity)
				.addValue(this.price)
				.addValue(this.description)
				.toString();
	}
	
}
