angular.module('learnspring', ["ui.router", "ngMaterial"])
	.config(function($mdThemingProvider) {
		$mdThemingProvider.definePalette('customBlue', {
			'50':'e3f2fd',
			'100':'bbdefb',
			'200':'90caf9',
			'300':'64b5f6',
			'400':'42a5f5',
			'500':'2196f3',
			'600':'1e88e5',
			'700':'1976d2',
			'800':'1565c0',
			'900':'0d47a1',
			'A100':'82b1ff',
			'A200':'448aff',
			'A400':'2979ff',
			'A700':'2962ff',
		    'contrastDefaultColor': 'light',    // whether, by default, text (contrast)
		                                        // on this palette should be dark or light
		    'contrastDarkColors': ['50', '100', //hues which contrast should be 'dark' by default
		     '200', '300', '400', 'A100'],
		    'contrastLightColors': undefined    // could also specify this if default was 'dark'
		  });
		 $mdThemingProvider.theme('default')
    		.primaryPalette('customBlue')
	})
	.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state('messages', {
				url : '/messages',
				templateUrl : 'app/messages/messages.jsp',
				controller : 'messagesController as mc'
			})
			.state('songList', {
				url : '/songlist',
				templateUrl : 'app/songlist/songList.jsp',
				controller : 'songListController as slc'
			})
			.state('account', {
				url : '/account', 
				templateUrl : 'app/account/account.jsp',
				controller : 'accountController as ac'
			});
		$urlRouterProvider.otherwise("/songlist");
		
	}])
	.config(['$qProvider', function ($qProvider) {
    	$qProvider.errorOnUnhandledRejections(false);
	}]);