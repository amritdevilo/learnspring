var app = angular.module("learnspring").controller("messagesController", messagesController);

messagesController.$inject = ["$rootScope", "$scope", "$http", "$mdToast", "$mdMedia", "$mdDialog"];

function messagesController($rootScope, $scope, $http, $mdToast, $mdMedia, $mdDialog) {
	var vm = this;
	$scope.defaultImageLink = "/learnspring/static/images/defaultImageLink.jpg";
	$scope.messageIndex = 0; // track loaded messages index
	$scope.currentPage = 0; // track current message batch being displayed
	vm.messageBatch = 4;
	
	$scope.messages = [];
	
	$scope.messageCurrent = []; 
	
	vm.nextMessageBatch = function nextMessageBatch() {
		$http.get("/learnspring/api/message/getAll/"+$scope.messageIndex+"/"+($scope.messageIndex + vm.messageBatch))
		.then(function(result){
			console.log(result);
			
			if (result.data.userMessage.length != 0) {
				$scope.messageCurrent = result.data.userMessage; //next batch to the current buffer
			} else {
				$scope.currentPage = Math.max($scope.currentPage - 1, 0);
			}
			
			result.data.userMessage.forEach(function(m){
				$scope.messages.push(m);
			});
			
			$scope.messageIndex += Math.min(vm.messageBatch, result.data.userMessage.length);
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
	};
	
	vm.prevBatch = function prevBatch() {
		$scope.currentPage = Math.max($scope.currentPage - 1, 0);
		var idx = $scope.currentPage * vm.messageBatch;
		$scope.messageCurrent = $scope.messages.slice(idx, idx + vm.messageBatch);
	};
	
	vm.nextBatch = function nextBatch() {
		$scope.currentPage += 1;
		var idx = $scope.currentPage * vm.messageBatch;
		if (idx >= $scope.messages.length) {
			vm.nextMessageBatch();
		}
		else {
			$scope.messageCurrent = $scope.messages.slice(idx, idx + vm.messageBatch);
		}
	};
	
	vm.importMessage = function importMessage(message) {
		$mdDialog.show({
			templateUrl : "/learnspring/app/modals/rateMessage/rateMessageModal.jsp",
			controller : "rateMessageModalController as rmc",
			disableParentScroll : true,
			hasBackdrop : true,
		}).then(function(result){
			var res = 0;
			if (result == undefined || result == null || result == 0) {
				res = 0;
			} else {
				res = result;
			}
			payload = {
				"songId" : message.songId,
				"rating" : res
			}
			
			$http.post("/learnspring/api/message/import", payload)
				.then(function(result){
					song = {
						"songName" : message.name,
						"link" : message.link,
						"rating" : res,
						"songId" : message.songId,
						"resource" : ""
					}
					$rootScope.songList.splice(0, 0, song);
					
					$mdToast.show(
				      $mdToast.simple()
				        .textContent("Song imported to library : " + result.data.status )
				        .position('top right')
				        .hideDelay(1000)
				    );
				}, function(result) {
					console.log(result);
					$mdToast.show(
				      $mdToast.simple()
				        .textContent("Something went wrong ! : " + result.data.message )
				        .position('top right')
				        .hideDelay(1000)
				    );
				});
		});
	};
	
	vm.thumbnail = function thumbnail(link) {
		re = /(?:https?:\/{2})?(?:w{3}\.)?youtu(?:be)?\.(?:com|be)(?:\/watch\?v=|\/)([^\s&]+)/;
		res = link.match(re);
		
		if (res == null || res.length < 2) {
			return $scope.defaultImageLink;
		} else {
			return "https://i3.ytimg.com/vi/" + res[1] + "/mqdefault.jpg";
		}
	}
	
	vm.constructName = function constructName(message) {
		return message.firstName + " " + message.lastName;
	}
	
	vm.getFormFlexValue = function() {
		if ($mdMedia("xs")) {
			vm.formFlexValue = 90;
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
