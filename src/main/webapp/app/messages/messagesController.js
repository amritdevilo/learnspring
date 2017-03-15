var app = angular.module("learnspring").controller("messagesController", messagesController);

messagesController.$inject = ["$rootScope", "$scope", "$http", "$mdToast", "$mdMedia", "$mdDialog"];

function messagesController($ootScope, $scope, $http, $mdToast, $mdMedia, $mdDialog) {
	var vm = this;
	
	vm.defaultImageLink = "/learnspring/static/images/defaultImageLink.jpg";
	$scope.messageIndex = 0;
	vm.messageBatch = 10;
	
	$scope.messages = [];
	
	vm.nextMessageBatch = function nextMessageBatch() {
		$http.get("/learnspring/api/message/getAll/"+$scope.messageIndex+"/"+($scope.messageIndex + vm.messageBatch))
		.then(function(result){
			console.log(result);
			result.data.userMessage.forEach(function(m){
				$scope.messages.push(m);
			});
			$scope.messageIndex += vm.messageBatch;
		}, 
		function(result){
			console.log(result);
			$mdToast.show(
		      $mdToast.simple()
		        .textContent("there's something wrong ! : " + result.data.status )
		        .position('top right')
		        .hideDelay(1000)
		    );
		});
	}
	
	vm.thumbnail = function thumbnail(link) {
		re = /(?:https?:\/{2})?(?:w{3}\.)?youtu(?:be)?\.(?:com|be)(?:\/watch\?v=|\/)([^\s&]+)/;
		res = link.match(re);
		
		if (res == null || res.length < 2) {
			return vm.defaultImageLink;
		} else {
			return "https://i3.ytimg.com/vi/" + res[1] + "/mqdefault.jpg";
		}
	}
	
	vm.constructName = function constructName(message) {
		return message.firstName + " " + message.lastName;
	}
	
	vm.getFormFlexValue = function() {
		if ($mdMedia("xs")) {
			vm.formFlexValue = 80;
		} else {
			vm.formFlexValue = 40;
		}
		
		return vm.formFlexValue;
	}
	
	vm.initMessagesController = function initMessagesController() {
		console.log("In message controller");
		vm.nextMessageBatch();
	}
	
	vm.initMessagesController();
}