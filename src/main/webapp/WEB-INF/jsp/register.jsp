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
	<div layout="row" ng-controller="registerController as rc">
		<div flex="10"></div>
		<!-- modelAttribute is model.get("attr") that contains the object to be filled in the form -->
		<form:form id="registerForm" action="register" method="POST" modelAttribute="user" flex="{{rc.getFormFlexValue()}}">
			<div class="md-padding" id="inputDiv" layout="column" >
				<h2>Register</h2>
				<md-input-container>
					<label>Email</label>
					<form:input path="email" ng-model="user.email" />
				</md-input-container>
				<md-input-container>
					<label>First Name</label>
					<form:input path="firstName" ng-model="user.firstName" />
				</md-input-container>
				<md-input-container>
					<label>Last Name</label>
					<form:input path="lastName" ng-model="user.lastName" />
				</md-input-container>
				<md-input-container>
					<label>Password</label>
					<form:input type="password" path="password" ng-model="user.password" />
				</md-input-container>
				<md-input-container>
					<label>Confirm Password</label>
					<input type="password" name="confPassword" ng-model="user.confPassword" />
				</md-input-container>
			
				<div layout="row" layout-align="center center">
					<md-button ng-click="rc.doSubmit()" flex class="md-raised md-primary">Submit</md-button>
				</div>
				
				<md-button class="md-primary md-raised" ng-href="login">Have an account? Log in</md-button>
			</form:form>
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
	<script src="https://cdn.jsdelivr.net/angular.youtube-embed/1.7.9/ng-youtube-embed.min.js"></script>
	
	<script src="<c:url value="/app/app.js" />"></script>
	<script src="<c:url value="/app/register/registerController.js" />"></script>
	
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
