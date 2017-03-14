<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div layout="row">
	<div flex="10"></div>
	<div ng-cloak flex="{{slc.getFormFlexValue()}}">
   		<md-content class="md-whiteframe-z2">
   			<md-list>
   				<md-list-item class="md-1-line" >
   					<div flex>
						<h3 layout-padding>Your Songs</h3>
					</div>
					<md-button ng-click="slc.addSong()">Add</md-button>
   				</md-list-item>
   				<md-list-item class="md-2-line" ng-repeat="song in songList">
   					<div flex="5"></div>
   					<img ng-src="{{slc.thumbnail(song.link)}}" class="md-avatar" alt="{{item.who}}" />
    				<div flex class="md-list-item-text" layout="column">
    					<h3><a ng-href="{{song.link}}" target="_blank">{{song.name}}</a></h3>
    				</div>
    				<div>
    					<md-button class="md-icon-button" ng-click="slc.sendMessage(song)">
    						<md-icon md-svg-icon="<c:url value="/static/images/ic_send_black_48px.svg" />" aria-label="send"></md-icon>
    					</md-button>
    				</div>
    				<md-divider></md-divider>
   				</md-list-item>
   			</md-list>
   		</md-content>
	</div>
</div>