var app = angular.module("learnspring").controller("sendMessageModalController", sendMessageModalController);

sendMessageModalController.$inject = ["$rootScope", "$scope", "$mdDialog", "$http", "song"];

function sendMessageModalController($rootScope, $scope, $mdDialog, $http, song) {
	var vm = this;
	
	console.log(song);
	$scope.song = song;
	vm.users;
	vm.toId = null;
	vm.searchText = null;
	
	vm.closeDialog = function closeDialog() {
		$mdDialog.hide();
	}
	
	vm.queryUsersList = function queryUsersList(key) {
//		console.log("searching with " + key);
		if (key == null || key == undefined || key.length == 0) return;
		
		return $http.get("/learnspring/api/userlist/" + key)
			.then(function(result) {
				vm.users = result.data.userlist;
//				console.log(result.data);
			});
	}
	
	vm.constructName = function constructName(user) {
		return user.firstName + " " + user.lastName;
	}
	
	vm.doSubmit = function doSubmit() {
		console.log(vm.toId);
		message = {
			"toId" : vm.toId.userId,
			"songId" : song.songId
		}
		
		$mdDialog.hide(message);
	};

	vm.initSendMessageModalController = function initSendMessageModalController() {
		console.log("in send message controller");
	} 

	vm.initSendMessageModalController();
}