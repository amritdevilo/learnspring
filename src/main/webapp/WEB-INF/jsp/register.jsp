<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="learnspring">
<head>
	<title>Learn spring</title>
	<meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no" >
	<link rel="stylesheet" href="<c:url value="/static/css/angular-material.min.css" />">
	<link rel="stylesheet" href="<c:url value="/static/css/fonts.css" />">
</head>
<body ng-controller="registerController as vm">
 <md-toolbar class="md-hue-2 md-whiteframe-3dp">
      <div class="md-toolbar-tools">
        <md-button class="md-icon-button" aria-label="Settings" ng-disabled="true">
          <md-icon md-svg-icon="img/icons/menu.svg"></md-icon>
        </md-button>
        <h2>
          <span>Toolbar with Disabled/Enabled Icon Buttons</span>
        </h2>
        <span flex></span>
        <md-button class="md-icon-button" aria-label="Favorite">
          <md-icon md-svg-icon="img/icons/favorite.svg" style="color: greenyellow;"></md-icon>
        </md-button>
        <md-button class="md-icon-button" aria-label="More">
          <md-icon md-svg-icon="img/icons/more_vert.svg"></md-icon>
        </md-button>
      </div>
    </md-toolbar>

	<div>
		<md-content>
			<md-input-container>
				<label>e-mail</label>
				<input type="text" ng-model="vm.email">
			</md-input-container>
			<md-button class="md-raised md-warn">Button</md-button>
		</md-content>
	<div>
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
