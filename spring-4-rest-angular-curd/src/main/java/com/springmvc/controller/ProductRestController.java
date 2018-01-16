package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springmvc.model.Product;
import com.springmvc.model.Product.ProductCategory;
import com.springmvc.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> products = productService.findAllProducts();

		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Product " + product.getDescription());

		if (productService.isProductExist(product)) {
			System.out.println("A Product with description " + product.getDescription() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		productService.saveProduct(product);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		System.out.println("Updating Product " + product.getDescription());

		Product currentProduct = productService.findById(id);

		if (currentProduct == null) {
			System.out.println("Product with id = " + id + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		currentProduct.setDescription(product.getDescription());
		currentProduct.setCategory(product.getCategory());

		productService.updateProduct(currentProduct);

		return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
		System.out.println("Fetching and deleting Product with id " + id);

		Product product = productService.findById(id);
		if (product == null) {
			System.out.println("Unable to delete. Product with id " + id + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		productService.deleteProductById(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/product/category/", method = RequestMethod.GET)
	public ResponseEntity<List<ProductCategory>> listAllCategories() {
		return new ResponseEntity<List<ProductCategory>>(ProductCategory.listAll(), HttpStatus.OK);
	}

}
