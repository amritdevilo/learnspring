//angular.module("learnspring").controller("registerController", registerController);
//
//registerController.$inject = ["$scope", "$rootScope"];
//
//function registerController($scope, $rootScope) {
//	var vm = this;
//	console.log("In registerController");
//	
//	vm.email;
//}

angular.module("learnspring")
	.controller("registerController", ["$scope", "$rootScope", function($scope, $rootScope) {
		console.log("in register controller");
		var vm = this;
//		vm.email = "samepl@gmail.com";
		vm.email;
	}]);