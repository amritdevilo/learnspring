<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="learnspring">
<head>
	<title>Learn spring</title>
	<meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no" >
	<link rel="stylesheet" href="<c:url value="/static/css/angular-material.min.css" />">
	<link rel="stylesheet" href="<c:url value="/static/css/fonts.css" />">
</head>
<body ng-cloak> <!--background="<c:url value="/static/images/background_img2.jpg" />"-->
	<!-- Template out -->
	<md-toolbar class="md-hue-2 md-whiteframe-z3">
		<div class="md-toolbar-tools">
			<h2>Learn Spring</h2>
		</div>
	</md-toolbar>
	
	<!-- global toast message -->
	{{msg = '${message}';""}}
<!-- 	{{message = "settle";"" }} -->
	<div layout="row" ng-controller="loginController as lc">
		<div flex="10"></div>
		<!-- modelAttribute is model.get("attr") that contains the object to be filled in the form -->
		<form id="loginForm" action="<c:url value="/login" />" method="POST"  flex="{{lc.getFormFlexValue()}}">
			<div class="md-padding" id="inputDiv" layout="column" >
				<h2>Login</h2>
				<md-input-container>
					<label>Email</label>
					<input id="email" name="email" ng-model="loginUser.email" autofocus="autofocus"/>
				</md-input-container>
				<md-input-container>
					<label>Password</label>
					<input id="password" name="password" type="password" ng-model="loginUser.password" />
				</md-input-container>
				<div layout="row" layout-align="center center">
					<md-button ng-submit="lc.doSubmit()" type="submit" flex class="md-raised md-primary">Submit</md-button>
				</div>
				 <input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
		<div flex></div>
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
	
	<script src="<c:url value="/app/app.js" />"></script>
	<script src="<c:url value="/app/login/loginController.js" />"></script>
	
	<style type="text/css">
		.scrollLock{
			position: fixed;
		}	  
		md-nav-bar {
  			background-color: #2196f3; 
  		}

  		md-sidenav, md-backdrop {
			position: fixed !important;
		}
	</style>
</html>
