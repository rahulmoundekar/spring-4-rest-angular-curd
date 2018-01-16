'use strict';

var App = angular.module('myApp',['ngRoute','ngResource']);

App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      // route for the home page
      .when('/', {
          templateUrl: '/products/',
          controller: 'ProductController'
      });
}]);