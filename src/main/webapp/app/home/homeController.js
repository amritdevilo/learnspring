angular.module("learnspring").controller("homeController", homeController);

homeController.$inject = ["$scope", "$rootScope", "$http"];

function homeController($scope, $rootScope, $http) {
	console.log("In homeController");
	$rootScope.isLoggedIn = true;
	var vm = this;
//	$rootScope.userInfo = $http.get("/learnspring/client").then(function(response) {
//		$rootScope.userInfo = response.data.userInfo;
//		// status = response.data.status
//		// message = response.data.message
//		console.log($rootScope.userInfo);
//	});
}