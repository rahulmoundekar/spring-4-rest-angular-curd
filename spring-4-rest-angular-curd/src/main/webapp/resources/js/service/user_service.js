'use strict';
 
App.factory('User', ['$resource', function($resource){
 
    return $resource(
    		'http://localhost:8095/spring-4-rest-angular-curd/user/:id',
    		{id: '@id'},
    		{
	            update: {
	                    method: 'PUT'
	            }
    		}         
    );
 
}]);