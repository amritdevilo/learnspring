var app = angular.module("learnspring").controller("sendMessageModalController", sendMessageModalController);

sendMessageModalController.$inject = ["$rootScope", "$scope", "$mdDialog", "$http", "song"];

function sendMessageModalController($rootScope, $scope, $mdDialog, $http, song) {
	var vm = this;
	
	$scope.song = song;
	$scope.users;
	$scope.toId;
	$scope.key
	
	vm.closeDialog = function closeDialog() {
		$mdDialog.hide();
	}
	
	vm.queryUsersList = function queryUsersList(key) {
		console.log("searching with " + key);
		return $http.get("/learnspring/api/userlist/" + key);
	}
	
	vm.searchTextChange = function searchTextChange(key) {
//		console.log(key);
	}
	
	vm.constructName = function constructName(user) {
		return user.firstName + " " + user.lastName;
	}
	
	vm.initSendMessageModalController = function initSendMessageModalController() {
		console.log("in send message controller");
	} 
	
	vm.initSendMessageModalController();
}