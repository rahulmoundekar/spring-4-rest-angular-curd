package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Product;

public interface ProductService {
	Product findById(long id);
	
	Product findByDescription(String description);
	
	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProductById(long id);
	
	List<Product> findAllProducts();
	
	void deleteAllPRoducts();

	public boolean isProductExist(Product product);

}
