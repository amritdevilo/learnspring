angular.module("learnspring").controller("headerController", headerController);

headerController.$inject = ["$rootScope", "$scope", "$state"];

function headerController($rootScope, $scope, $state) {
	var vm = this;
	vm.doLogOut = function() {
		console.log("logging out");
		$rootScope.isLoggedIn = false;
		document.getElementById('logoutForm').submit();
	}
	
	vm.dropDownList = {};
	
	vm.dropDownList["Account"] = function() {$state.go("account");}
	vm.dropDownList["Logout"]  = function() {vm.doLogOut();}
	
	vm.dropDownListKeys = Object.keys(vm.dropDownList);
	
	vm.dropDownListAction = function(item) {
		vm.dropDownList[item]();
	}
	
	vm.initHeaderController = function initHeaderController() {
		console.log("in header controller");
		console.log("Is logged in " + $rootScope.isLoggedIn);
	}
	
	vm.initHeaderController();
};