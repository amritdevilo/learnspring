var app = angular.module("learnspring").controller("addSongModalController", addSongModalController);

addSongModalController.$inject = ["$rootScope", "$scope", "$mdMedia", "$mdDialog"];

function addSongModalController($rootScope, $scope, $mdMedia, $mdDialog) {
	var vm = this;
	
	$scope.song = {
		"name" : "",
		"link" : "",
		"resource" : ""
	}
	
	//TODO write validator code
	
	vm.doSubmit = function doSubmit() {
		$mdDialog.hide($scope.song);
	};
	
	vm.closeDialog = function closeDialog() {
		$mdDialog.hide();
	};
	
	
	vm.initAddSongModalController = function initAddSongModalController() {
		console.log("in add song modal controller");
	}
	
	vm.initAddSongModalController();
} 
