'use strict';

App.controller('ProductController', ['$scope', 'ProductService', function($scope, ProductService) {
	var self = this;	
	self.product={id:null,description:'',category:null};
	self.products=[];
	
	self.categories=[];
	
	self.fetchAllProducts = function(){
		ProductService.fecthAllProducts()
			.then(
					function(d) {
						self.products = d;						
					},
					function(errReponse){
						console.error('Error while fetching Products');
					}
			);
	};
	
	self.fetchAllProducts();
		
	self.fetchAllCategories = function(){
		ProductService.fetchAllCategories()
			.then(
					function(d){
						self.categories = d;
					},
					function(errResponse){
						console.error('Error while fetching Categories');
					}
			);
	};
	
	self.fetchAllCategories();
	
	self.reset = function(){
		self.product = {id:null,description:'',category:null};
		$scope.myForm.$setPristine();
	};
	
	self.submit = function(){
		if(self.product.id==null){
			console.log('Saving New Product', self.product);
			self.createProduct(self.product);
		}else{
			self.updateProduct(self.product, self.product.id);
			console.log('Product update with id ', self.product.id);
		}
		self.reset();
	};
	
	self.createProduct = function(product){
		ProductService.createProduct(product)
			.then(
					self.fetchAllProducts,
						function(errResponse){
							console.error('Error while creating Product');
						}
			);
	};
	
	self.edit = function(id){
		console.log('id to be edited', id);
		for(var i = 0; i < self.products.length; i++){
			if(self.products[i].id == id){
				self.product = angular.copy(self.products[i]);
				break;
			}
		}
	};
	
	self.updateProduct = function(product, id){
		console.log('product to be edited', product);
		ProductService.updateProduct(product, id)
			.then(
					self.fetchAllProducts,
						function(errResponse){
							console.error('Error while updating Product')
					}
			);
	};
	
	self.remove = function(id){
		console.log('id to be deleted', id);
		if(self.product.id == id){
			self.reset();
		}
		self.deleteProduct(id);
	}
	
	self.deleteProduct = function(id){		
		ProductService.deleteProduct(id)
			.then(
					self.fetchAllProducts,
					function(errResponse){
						console.error('Error while deleting Product');
					}
			);
	}
	
}]);