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
		<ui-view></ui-view>
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
	<script src="<c:url value="/app/songlist/songListController.js" />"></script>
	<script src="<c:url value="/app/messages/messagesController.js" />"></script>
	<script src="<c:url value="/app/account/accountController.js" />"></script>
	<script src="<c:url value="/app/modals/addSongs/addSongModalController.js" />"></script>
	<script src="<c:url value="/app/modals/sendMessage/sendMessageModalController.js" />"></script>
	<script src="<c:url value="/app/modals/rateMessage/rateMessageModalController.js" />"></script>
</html>