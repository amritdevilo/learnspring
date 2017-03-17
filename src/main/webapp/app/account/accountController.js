angular.module("learnspring").controller("accountController", accountController);

accountController.$inject = ["$rootScope", "$scope", "$http", "$mdToast", "$mdMedia", "$mdDialog", "$timeout"];

function accountController($rootScope, $scope, $http, $mdToast, $mdMedia, $mdDialog, $timeout) {
	var vm = this;
	vm.passwordRegEx = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	
	$scope.client = $rootScope.client;
	
	$scope.oldPasswd;
	$scope.newPasswd;
	$scope.confPasswd;
	
	vm.updateClient = function updateClient() {
		payload = {
			"oldPassword" : $scope.oldPasswd,
			"newPassword" : $scope.newPasswd
		}
		
		console.log($scope.oldPasswd);
		console.log($scope.newPasswd);
		$http.post("/learnspring/api/client/update", payload)
			.then(function(result){
				$mdToast.show(
			      $mdToast.simple()
			        .textContent("Update Accepted ! Redirecting to login")
			        .position('top right')
			        .hideDelay(1000)
			    );
				$timeout(function(){
					document.location = "/learnspring/login";
				}, 1000);
				
			}, function(result) {
				$mdToast.show(
			      $mdToast.simple()
			        .textContent("Update Rejected : " + result.data.message)
			        .position('top right')
			        .hideDelay(1000)
			    );
				$scope.oldPasswd = "";
				$scope.newPasswd = "";
				$scope.confPasswd = "";
			});
		
	}
	
	vm.checkClientInfo = function checkClientInfo() {
		if ($rootScope.client == null || $rootScope.client == undefined) {
			$http.get("/learnspring/api/client")
				.then(function(result){
					$rootScope.client = result.data.userInfo;
					$scope.client = result.data.userInfo;
					$rootScope.isLoggedIn = true;
					console.log("client info loaded");
					console.log($rootScope.client);
				}, function(result) {
					$mdToast.show(
				      $mdToast.simple()
				        .textContent("Client Info could not be retrieved : " + result.data.message)
				        .position('top right')
				        .hideDelay(1000)
				    );
				});
		}
	}
	
	vm.getFormFlexValue = function() {
		if ($mdMedia("xs")) {
			vm.formFlexValue = 80;
		} else {
			vm.formFlexValue = 40;
		}
		
		return vm.formFlexValue;
	}
	
	vm.accountController = function accountController() {
		console.log("In account controller");
		vm.checkClientInfo();
	}
	
	vm.accountController();
}