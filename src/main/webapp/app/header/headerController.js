angular.module("learnspring").controller("headerController", headerController);

headerController.$inject = ["$rootScope", "$scope"];

function headerController($rootScope, $scope) {
	var vm = this;
	vm.doLogOut = function() {
		console.log("logging out");
		$rootScope.isLoggedIn = false;
		document.getElementById('logoutForm').submit();
	}
	
	vm.dropDownList = {};
	
	vm.dropDownList["Account"] = function() {console.log("account");}
	vm.dropDownList["Logout"]  = function() {vm.doLogOut();}
	
	vm.dropDownListKeys = Object.keys(vm.dropDownList);
	
	vm.dropDownListAction = function(item) {
		vm.dropDownList[item]();
	}
};