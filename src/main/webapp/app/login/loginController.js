var app = angular.module("learnspring");
app.controller("loginController", loginController);

loginController.$inject = ["$rootScope", "$scope", "$mdToast", "$mdMedia"];

function loginController($rootScope, $scope, $mdToast, $mdMedia) {
	var vm = this;
	vm.formFlexValue = 30;
	vm.getFormFlexValue = function() {
		if ($mdMedia("xs")) {
			vm.formFlexValue = 70;
		} else {
			vm.formFlexValue = 30;
		}
		
		return vm.formFlexValue;
	}
	
	$scope.loginUser = {}
	$scope.loginUser.email;
	$scope.loginUser.password;
	
	vm.passwordRegEx = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	vm.emailRegEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	vm.doSubmit = function() {
		//form validations
		var isValid = true;
		if (isValid && !validateEmail($scope.loginUser.email)) {
			isValid = false;
			$rootScope.msg = "Invalid email address";
		}
		if (isValid && !validatePassword($scope.loginUser.password)) {
			isValid = false;
			$rootScope.msg = "Invalid password";
		}
		if (isValid) {
			document.getElementById('loginForm').submit();
		} else {
			$mdToast.show(
		      $mdToast.simple()
		        .textContent($rootScope.msg)
		        .position('top right')
		        .hideDelay(1000)
		    );
		}
	};
	
	function validatePassword(password) {
		var reg = new RegExp(vm.passwordRegEx);
		return reg.test(password);
	}

	function validateEmail(email) {
		var reg = new RegExp(vm.emailRegEx);
		return reg.test(email);
	}

	function validateName(userName) {
		var reg = new RegExp(vm.userNameRegEx);
		return reg.test(userName);
	}
	
	vm.showFlashMsg = function() {
		$mdToast.show(
	      $mdToast.simple()
	        .textContent($rootScope.msg)
	        .position('top right')
	        .hideDelay(1000)
	    );
	};
	angular.element(document).ready(function(){
		if ($rootScope.msg != "")
			vm.showFlashMsg();
	});
}