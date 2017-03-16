<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div layout="row">
	<div flex="5"></div>
	<div ng-cloak flex="{{mc.getFormFlexValue()}}">
   		<md-content class="md-whiteframe-z2">
   			<md-list>
   				<md-subheader class="md-no-sticky">
   					<div layout="row">
   						<div flex><p style="vertical-align: middle">Your messages</p></div>
   						<md-button class="md-icon-button" ng-click="mc.prevBatch()">
   							<md-icon md-svg-icon="<c:url value="/static/images/ic_keyboard_arrow_left_black_24px.svg" />" aria-label="next"></md-icon>
   						</md-button>
   						<md-button class="md-icon-button" ng-click="mc.nextBatch()">
   							<md-icon md-svg-icon="<c:url value="/static/images/ic_keyboard_arrow_right_black_24px.svg" />" aria-label="prev"></md-icon>
   						</md-button>
   					</div>
   				</md-subheader>
   				<md-list-item class="md-2-line" ng-repeat="message in messageCurrent track by $index">
   					<div flex="5"></div>
   					<img ng-src="{{mc.thumbnail(message.link)}}" class="md-avatar" alt="{{message.link}}" />
    				<div class="md-list-item-text" layout="column">
    					<h3><a ng-href="{{message.link}}" target="_blank">{{message.name}}</a></h3>
    					<p>{{mc.constructName(message)}}</p>
    				</div>
    				<div>
    					<md-button class="md-icon-button" ng-click="mc.importMessage(message)">
    						<md-icon md-svg-icon="<c:url value="/static/images/ic_cloud_download_black_24px.svg" />" aria-label="import"></md-icon>
    					</md-button>
    				</div>
    				<md-divider ng-if="!$last"></md-divider>
   				</md-list-item>
   			</md-list>
   		</md-content>
	</div>
</div>