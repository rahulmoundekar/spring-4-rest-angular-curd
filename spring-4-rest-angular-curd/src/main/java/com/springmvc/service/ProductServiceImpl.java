package com.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.Product;
import com.springmvc.model.Product.ProductCategory;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Product> products;

	static {
		products = populateProducts();
	}

	public Product findById(long id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	private static List<Product> populateProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(counter.incrementAndGet(), "Arroz",
				ProductCategory.Grocery));
		products.add(new Product(counter.incrementAndGet(), "Detergente",
				ProductCategory.Cleaning));
		products.add(new Product(counter.incrementAndGet(), "Batedeira",
				ProductCategory.Electronic));
		products.add(new Product(counter.incrementAndGet(), "Creme Dental",
				ProductCategory.Hygiene));
		products.add(new Product(counter.incrementAndGet(), "Milho",
				ProductCategory.Grocery));
		products.add(new Product(counter.incrementAndGet(), "Lâmpada",
				ProductCategory.Electronic));
		return products;
	}

	public Product findByDescription(String description) {
		for (Product product : products) {
			if (product.getDescription().equalsIgnoreCase(description)) {
				return product;
			}
		}
		return null;
	}

	public void saveProduct(Product product) {
		product.setId(counter.incrementAndGet());
		products.add(product);
	}

	public void updateProduct(Product product) {
		int index = products.indexOf(product);
		products.set(index, product);
	}

	public void deleteProductById(long id) {
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product product = iterator.next();
			if (product.getId() == id) {
				iterator.remove();
			}
		}
	}

	public List<Product> findAllProducts() {
		return products;
	}

	public void deleteAllPRoducts() {
		products.clear();
	}

	public boolean isProductExist(Product product) {
		return findByDescription(product.getDescription()) != null;
	}

}
