angular.module("learnspring").controller("homeController", homeController);

homeController.$inject = ["$scope", "$rootScope", "$http", "$mdToast", "$mdMedia", "$mdDialog"];

function homeController($scope, $rootScope, $http, $mdToast, $mdMedia, $mdDialog) {
	$rootScope.isLoggedIn = true;
	var vm = this;
	vm.getFormFlexValue = function() {
		if ($mdMedia("xs")) {
			vm.formFlexValue = 80;
		} else {
			vm.formFlexValue = 40;
		}
		
		return vm.formFlexValue;
	}
	
	vm.initHomeController = function initHomeController() {
		console.log("in home controller");
	}
	
	vm.initHomeController();
}