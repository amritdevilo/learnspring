<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div layout="row">
	<div flex="10"></div>
	<div ng-cloak flex="{{mc.getFormFlexValue()}}">
   		<md-content class="md-whiteframe-z2">
   			<md-list>
   				<md-subheader class="md-no-sticky">
   					Your messages
   				</md-subheader>
   				<md-list-item class="md-2-line" ng-repeat="message in messages">
   					<div flex="5"></div>
   					<img ng-src="{{mc.thumbnail(message.link)}}" class="md-avatar" alt="{{message.link}}" />
    				<div class="md-list-item-text" layout="column">
    					<h3><a ng-href="{{message.link}}" target="_blank">{{message.name}}</a></h3>
    					<p>{{mc.constructName(message)}}</p>
    				</div>
    				<md-divider></md-divider>
   				</md-list-item>
   			</md-list>
   		</md-content>
	</div>
</div>