angular.module("learnspring").controller("songListController", songListController);

songListController.$inject = ["$scope", "$rootScope", "$http", "$mdToast", "$mdMedia", "$mdDialog"];

function songListController($scope, $rootScope, $http, $mdToast, $mdMedia, $mdDialog) {
	$rootScope.isLoggedIn = true;
	var vm = this;
	vm.getFormFlexValue = function() {
		if ($mdMedia("xs")) {
			vm.formFlexValue = 80;
		} else {
			vm.formFlexValue = 40;
		}
		
		return vm.formFlexValue;
	}
	
	$scope.songList;
	$scope.song = {
		"name" : "",
		"link" : "",
		"resource" : ""
	}
	$scope.defaultImageLink = "/learnspring/static/images/defaultImageLink.jpg";
	$scope.messages;
	
	vm.addSong = function addSong() {
		$mdDialog.show({
			templateUrl : "/learnspring/app/modals/addSongs/addSongModal.jsp",
			controller : "addSongModalController as asc",
			disableParentScroll : true,
			hasBackdrop : true,
		}).then(function(result){
			$scope.song = result;
			console.log($scope.song);
			vm.doSubmit();
		});
	};
	
	vm.doSubmit = function doSubmit() {
		console.log("in submit");
		console.log($scope.song);
		if ($scope.song != undefined) {
			$http.post("/learnspring/api/song/add", $scope.song)
				.then(function(result){
					console.log(result.data);
					$scope.songList.splice(0, 0, result.data.song);
					$mdToast.show(
				      $mdToast.simple()
				        .textContent("Song added Success : " + result.data.status )
				        .position('top right')
				        .hideDelay(1000)
				    );
				}
				, function(result){
					console.log(result.data);
					$mdToast.show(
				      $mdToast.simple()
				        .textContent("Song adding failed : " + result.data.status )
				        .position('top right')
				        .hideDelay(1000)
				    );
				});
		}
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
	
	vm.sendMessage = function sendMessage(song) {
		$mdDialog.show({
			templateUrl : "/learnspring/app/modals/sendMessage/sendMessageModal.jsp",
			controller : "sendMessageModalController as smc",
			disableParentScroll : true,
			hasBackdrop : true,
			locals : {
				song : song
			}
		}).then(function(result){
			if (result == null || result == undefined) return ;
			console.log(result);
			$http.post("/learnspring/api/message/add", result)
				.then(function(result){
					console.log(result);
					var msg = "Message " + (result.status == 200 ? "" : "not") + " sent : " + result.status;
					$mdToast.show(
				      $mdToast.simple(msg)
				        .position('top right')
				        .hideDelay(1000)
				    );
				}, function(result){
					console.log(result);
					var msg = "Message " + (result.status == 200 ? "" : "not") + " sent : " + result.status;
					$mdToast.show(
				      $mdToast.simple(msg)
				        .position('top right')
				        .hideDelay(1000)
				    );
				});
		});
	};
	
	vm.initHomeController = function initHomeController() {
		console.log("in home controller");
		
		//load songs
		$http.get("/learnspring/api/song/list")
			.then(function(result){
				$scope.songList = result.data;
				console.log("Song list fetched");
				console.log($scope.songList);
			}, function(result){
				$mdToast.show(
			      $mdToast.simple()
			        .textContent("Song list could not be fetched")
			        .position('top right')
			        .hideDelay(1000)
			    );
			});
	}
	
	vm.initHomeController();
}