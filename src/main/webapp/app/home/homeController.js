angular.module("learnspring").controller("homeController", homeController);

homeController.$inject = ["$scope", "$rootScope"];

function homeController($scope, $rootScope) {
	console.log("In homeController");
	$rootScope.isLoggedIn = true;
	var vm = this;
	
}