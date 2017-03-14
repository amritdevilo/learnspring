var app = angular.module("learnspring").controller("messagesController", messagesController);

messagesController.$inject = ["$rootScope", "$scope", "$http", "$mdToast", "$mdMedia", "$mdDialog"];

function messagesController($ootScope, $scope, $http, $mdToast, $mdMedia, $mdDialog) {
	var vm = this;
	
	vm.initMessagesController = function initMessagesController() {
		console.log("In message controller");
	}
	
	vm.initMessagesController();
}