<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html ng-app="learnspring">
	<head>
		<title>Learn spring</title>
		<meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no" >
		<link rel="stylesheet" href="<c:url value="/static/css/angular-material.min.css" />">
		<link rel="stylesheet" href="<c:url value="/static/css/fonts.css" />">
	</head>
	
	<body ng-hide ng-controller="homeController as hc">
		<header ng-include="'<c:url value="/app/header/header.jsp"/>'"></header>
		
		<div layout="row">
			<div flex="10"></div>
			<div ng-cloak flex="{{hc.getFormFlexValue()}}">
	    		<md-content class="md-whiteframe-z2">
	    			<md-list>
	    				<md-list-item class="md-1-line" >
	    					<div flex>
								<h2 layout-padding>Your Songs</h2>
							</div>
							<md-button ng-click="hc.addSong()">Add</md-button>
	    					<md-divider ></md-divider>
	    				</md-list-item>
	    				<md-list-item class="md-2-line" ng-repeat="song in songList">
	    					<div flex="5"></div>
	    					<img ng-src="{{hc.thumbnail(song.link)}}" class="md-avatar" alt="{{item.who}}" />
		    				<div flex class="md-list-item-text" layout="column">
		    					<h3><a ng-href="{{song.link}}" target="_blank">{{song.name}}</a></h3>
		    				</div>
		    				<div>
		    					<md-button class="md-icon-button" ng-click="hc.sendMessage(song)">
		    						<md-icon md-svg-icon="<c:url value="/static/images/ic_send_black_48px.svg"/>" aria-label="send"></md-icon>
		    					</md-button>
		    				</div>
		    				<md-divider></md-divider>
	    				</md-list-item>
	    			</md-list>
	    		</md-content>
			</div>
		</div>
		
	</body>
	<script src="<c:url value="/static/js/angular.min.js" />"></script>
	<script src="<c:url value="/static/js/angular-animate.min.js" />"></script>
	<script src="<c:url value="/static/js/angular-messages.min.js" />"></script>
	<script src="<c:url value="/static/js/angular-aria.min.js" />"></script>
	<script src="<c:url value="/static/js/angular-ui-router.min.js" />"></script>
	<script src="<c:url value="/static/js/angular-material.min.js" />"></script>
	<script src="<c:url value="/static/js/ui-bootstrap.min.js" />"></script>
	<script src="<c:url value="/static/js/ui-bootstrap-tpls.min.js" />"></script>
	
<%-- 	<script src="<c:url value="/app/app.js" />"></script> --%>
	<script src="app/app.js"></script>
	<script src="<c:url value="/app/home/homeController.js" />"></script>
	<script src="<c:url value="/app/header/headerController.js" />"></script>
	<script src="<c:url value="/app/modals/addSongs/addSongModalController.js" />"></script>
	<script src="<c:url value="/app/modals/sendMessage/sendMessageModalController.js" />"></script>
</html>