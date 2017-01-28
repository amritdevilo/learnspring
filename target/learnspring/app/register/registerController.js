angular.module("learnspring").controller("registerController", registerController);

registerController.$inject = ["$scope", "$rootScope", "$mdToast"];

function registerController($scope, $rootScope, $mdToast) {
	var vm = this;
	console.log("In registerController");
	$scope.user = {};
	$scope.user.email;
	$scope.user.firstName;
	$scope.user.lastName;
	$scope.user.password;
	$scope.user.confPassword;
	
	vm.userNameRegEx = /^[a-z][\w\.]{0,24}$/i;
	vm.passwordRegEx = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	vm.emailRegEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
	
	vm.doSubmit = function() {
		//form validations
		var isValid = true;
		var msg = "";
		if (isValid && !validateName($scope.user.firstName)) {
			isValid = false;
			msg = "Invalid first name";
		}
		if (isValid && !validateName($scope.user.lastName)) {
			isValid = false;
			msg = "Invalid last name";
		}
		if (isValid && !validateEmail($scope.user.email)) {
			isValid = false;
			msg = "Invalid email address";
		}
		if (isValid && !validatePassword($scope.user.password)) {
			isValid = false;
			msg = "Invalid password";
		}
		if (isValid && $scope.user.password != $scope.user.confPassword) {
			isValid = false;
			msg = "passwords do not match";
		}
		
		if (isValid) {
			document.getElementById('registerForm').submit();
		} else {
			$mdToast.show(
		      $mdToast.simple()
		        .textContent(msg)
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
}
