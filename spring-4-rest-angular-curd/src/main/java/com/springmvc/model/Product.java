package com.springmvc.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Product {

	public enum ProductCategory {
		Hygiene, Grocery, Cleaning, Electronic;

		public static List<ProductCategory> listAll() {
			List<ProductCategory> list = new ArrayList<ProductCategory>(EnumSet.allOf(ProductCategory.class));
			return list;

		}
	}

	private long id;

	private String description;

	private ProductCategory category;

	public Product() {
		id = 0;
	}

	public Product(long id, String description, ProductCategory category) {
		this.id = id;
		this.description = description;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
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
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
