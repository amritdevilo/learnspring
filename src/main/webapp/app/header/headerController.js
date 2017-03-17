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
	
	vm.dropDownList["Account"] = function() {$state.transitionTo('account');}
	vm.dropDownList["Logout"]  = function() {vm.doLogOut();}
	
	vm.dropDownListKeys = Object.keys(vm.dropDownList);
	
	vm.dropDownListAction = function(item) {
		vm.dropDownList[item]();
	}
};