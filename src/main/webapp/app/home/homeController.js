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
		
		//load songs
		$http.get("/learnspring/api/song/list")
			.then(function(result){
				$rootScope.songList = result.data;
				console.log("Song list fetched");
				console.log($rootScope.songList);
			}, function(result){
				$mdToast.show(
			      $mdToast.simple()
			        .textContent("Song list could not be fetched")
			        .position('top right')
			        .hideDelay(1000)
			    );
			});
	}
	
	vm.initHomeController();
}