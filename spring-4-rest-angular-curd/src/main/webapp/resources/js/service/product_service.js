'use strict';

App.factory('ProductService', ['$http', '$q', function($http, $q){
	
	return{
		fecthAllProducts: function(){
			return $http.get('http://localhost:8095/spring-4-rest-angular-curd/product/')
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while fetching products');
								return $q.reject(errResponse);
							}
					
					);
		},
		
		fetchAllCategories: function(){
			return $http.get('http://localhost:8095/spring-4-rest-angular-curd/product/category/')
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while fetching categories');
								return $q.reject(errResponse);
							}
					);
		},
		
		createProduct: function(product){
			return $http.post('http://localhost:8095/spring-4-rest-angular-curd/product/', product)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while creating product');
								return $q.reject(errResponse);
							}
					);
		},
		
		updateProduct: function(product, id){
			return $http.put('http://localhost:8095/spring-4-rest-angular-curd/product/' + id, product)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while updating product');
								return $q.reject(errResponse);
							}
					);
		},
		
		deleteProduct: function(id){
			return $http.delete('http://localhost:8095/spring-4-rest-angular-curd/product/' + id)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while deleting product');
								return $q.reject(errResponse);
							}
					);
		}
		
	};
	
}]);