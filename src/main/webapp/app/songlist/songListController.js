angular.module("learnspring").controller("songListController", songListController);

songListController.$inject = ["$scope", "$rootScope", "$http", "$mdToast", "$mdMedia", "$mdDialog"];

function songListController($scope, $rootScope, $http, $mdToast, $mdMedia, $mdDialog) {
	$rootScope.isLoggedIn = true;
	var vm = this;
	vm.video_url = "https://www.youtube.com/watch?v=iWnxbI9RFeQ";
	vm.video_url_2 = "https://www.youtube.com/watch?v=D0ag0dsP5B4";
	
	vm.change = function change() {
		vm.video_url = vm.video_url_2;
	}
	vm.screen_size = $mdMedia('xs');
	vm.getFormFlexValue = function() {
		if ($mdMedia("xs")) {
			vm.formFlexValue = 90;
		} else {
			vm.formFlexValue = 40;
		}
		
		return vm.formFlexValue;
	}

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
					$rootScope.songList.splice(0, 0, result.data.song);
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
	
	vm.deleteSong = function deleteSong(song) {
		$http.delete("/learnspring/api/song/delete/" + song.songId)
			.then(function(result){
					if ($rootScope.songList != undefined || $rootScope.songList != null || $rootScope.songList.length != 0) {
						var idx = -1;
						$rootScope.songList.forEach(function(s, index){
							if (s.songId == song.songId) {
								idx = index;
							}
						})
						if (idx != -1) {
							$rootScope.songList.splice(idx, 1);
						}
						$mdToast.show(
					      $mdToast.simple("song " + song.songName + " deleted : " + result.status)
					        .position('top right')
					        .hideDelay(1000)
					    );
					}
			}, function(result){
					$mdToast.show(
				      $mdToast.simple("song " + song.songName + " delete failed ! : " + result.status)
				        .position('top right')
				        .hideDelay(1000)
					);
			});
	}
	
	vm.showConfirm = function showConfirm(ev, song) {
	    var confirm = $mdDialog.confirm()
	          .title('Delete song?')
	          .textContent('Do you want to delete this song?')
	          .ariaLabel('del_song')
	          .targetEvent(ev)
	          .ok('Yes')
	          .cancel('No');

	    $mdDialog.show(confirm).then(function() {
	    	vm.deleteSong(song);
	    }, function() {
	    	
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
	
	vm.initSongListController = function initSongListController() {
		console.log("in songlist controller");
	}
	
	vm.initSongListController();
}