angular.module("learnspring").controller("rateMessageModalController", rateMessageModalController);

rateMessageModalController.$inject = ["$rootScope", "$scope", "$mdDialog"];

function rateMessageModalController($rootScope, $scope, $mdDialog) {
	var vm = this;
	
	$scope.rating=0;
	
	vm.closeDialog = function closeDialog() {
		$mdDialog.hide($scope.rating);
	}
	
	vm.cancelDialog = function cancelDialog() {
		$mdDialog.hide();
	}
	
	vm.initRateMessageModalController = function initRateMessageModalController() {
		console.log("in rate message modal controller");
	}
	
	vm.initRateMessageModalController();
}