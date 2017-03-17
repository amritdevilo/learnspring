angular.module("learnspring").controller("accountController", accountController);

accountController.$inject = ["$rootScope", "$scope", "$http", "$mdToast", "$mdMedia", "$mdDialog"];

function accountController($rootScope, $scope, $http, $mdToast, $mdMedia, $mdDialog) {
	var vm = this;
	vm.passwordRegEx = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	
	$scope.client = angular.copy($rootScope.client);
	
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
			        .textContent("Update Accepted !")
			        .position('top right')
			        .hideDelay(1000)
			    );
			}, function(result) {
				$mdToast.show(
			      $mdToast.simple()
			        .textContent("Update Rejected : " + result.data.message)
			        .position('top right')
			        .hideDelay(1000)
			    );
			});
	}
	
	vm.checkClientInfo = function checkClientInfo() {
		if ($rootScope.client == null) {
			$http.get("/learnspring/api/client")
				.then(function(result){
					$rootScope.client = result.data.userInfo;
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