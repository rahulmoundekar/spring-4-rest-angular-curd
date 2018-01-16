'use strict';

App.controller('UserController', ['$scope', 'User', function($scope, User) {
			var self = this;
			self.user = new User();
			self.users = [];

			self.fetchAllUsers = function() {
				self.users = User.query();
			};

			self.createUser = function() {
				console.log('Iserting user with username', self.user.username);
				self.user.$save(function() {
					self.fetchAllUsers();
				});
			};

			self.updateUser = function() {
				self.user.$update(function(){
					self.fetchAllUsers();
				});
			};

			self.deleteUser = function(identity) {
				var user = User.get({id:identity}, function(){
					user.$delete(function(){
						console.log('Deleting user with id ', identity);
						self.fetchAllUsers();
					});
				});
			};

			self.fetchAllUsers();

			self.submit = function() {
				alert(self.user.id);
				if (self.user.id == null) {
					console.log('Saving New User', self.user);
					self.createUser();
				} else {
					console.log('Upddating user with id ', self.user.id);
					self.updateUser();
					console.log('User updated with id ', self.user.id);
				}
				self.reset();
			};

			self.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < self.users.length; i++) {
					if (self.users[i].id === id) {
						self.user = angular.copy(self.users[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.user.id === id) {// clean form if the user to be
											// deleted is shown there.
					self.reset();
				}
				self.deleteUser(id);
			};

			self.reset = function() {
				self.user = new User();
				$scope.myForm.$setPristine(); // reset Form
			};

		}]);