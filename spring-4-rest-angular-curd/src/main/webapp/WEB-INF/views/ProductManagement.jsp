<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Product Management</title>
<style>
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular-route.js"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/service/product_service.js' />"></script>
<script
	src="<c:url value='/resources/js/controller/product_controller.js' />"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet" />
</head>
<body ng-app="myApp">
	<div ng-controller="ProductController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Product Registration Form</span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.product.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label">Id</label> <label
								class="col-md-1 control-label" style="text-align: left"
								ng-bind="ctrl.product.id"></label>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="udescription">Product</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.product.description"
									id="udescription" class="description form-control input-sm"
									placeholder="Enter Product's Name" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.udescription.$error.required">This
										is a required field</span> <span
										ng-show="myForm.udescription.$invalid">This field is
										invalid </span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="ucategory">Category</label>
							<div class="col-md-7">
								<select id="ucategory"
									class="category form-control selectpicker"
									ng-model="ctrl.product.category"
									ng-options="u for u in ctrl.categories track by u">
									<!-- 									<option ng-repeat="u in ctrl.categories" ng-bind="u" value="{{u}}"></option> -->
								</select>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.product.id ? 'Add' : 'Update' }}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button>
						</div>
					</div>
				</form>
			</div>
			<div class="panel-heading">
				<span class="lead">List of Products</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Product</th>
							<th>Category</th>
							<th width="25%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.products">
							<td><span ng-bind="u.id"></span></td>
							<td><span ng-bind="u.description"></span></td>
							<td><span ng-bind="u.category"></span></td>
							<td>
								<button type="button" ng-click="ctrl.edit(u.id)"
									class="btn btn-success custom-width">Edit</button>
								<button type="button" ng-click="ctrl.remove(u.id)"
									class="btn btn-danger custom-width">Remove</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>

</body>
</html>