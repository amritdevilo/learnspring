<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div ng-controller="headerController as hdrc">
	<md-toolbar class="md-hue-2 md-whiteframe-z3">
		<div class="md-toolbar-tools">
				<h2 flex>listentothis</h2>
			<md-button class="md-icon-button" aria-label="songs" ng-if="isLoggedIn" >
				<a ui-sref="songList" ui-sref-active="active"><md-icon md-svg-icon="<c:url value="/static/images/ic_queue_music_white_24px.svg"/>"></md-icon></a>
			</md-button>
			<md-button class="md-icon-button" aria-label="messages" ng-if="isLoggedIn" >
				<a ui-sref="messages" ui-sref-active="active"><md-icon md-svg-icon="<c:url value="/static/images/ic_email_white_24px.svg"/>"></md-icon></a>
			</md-button>
			<md-menu md-position-mode="target-right target" ng-if="isLoggedIn">
				<md-button aria-label="user-settings" ng-click="$mdOpenMenu()" class="md-icon-button">
					<md-icon md-svg-icon="<c:url value="/static/images/ic_settings_white_24px.svg" />"></md-icon>
				</md-button>
				<md-menu-content width="3">
					<md-menu-item ng-repeat="item in hdrc.dropDownListKeys">
						<md-button ng-click="hdrc.dropDownListAction(item)">
							<div layout="row" flex>
								<p>{{item}}</p>
							</div>
						</md-button>
					</md-menu-item>
				</md-menu-content>
			</md-menu>
		</div>
	</md-toolbar>
	<form id="logoutForm" action='<c:url value="/logout" />' method="post" ng-if="isLoggedIn">
		<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
	</form>
</div>