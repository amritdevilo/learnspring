<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div layout="row">
	<div flex="5"></div>
	<div ng-cloak flex="{{slc.getFormFlexValue()}}">
   		<md-content class="md-whiteframe-z2">
   			<md-list>
   				<md-subheader class="md-no-sticky">
   					<div layout="row">
   						<div><p style="vertical-align:middle">Your songs</p></div>
   						<md-button ng-click="slc.addSong()">Add</md-button>
   					</div>
   				</md-subheader>
   				<md-list-item class="md-2-line" ng-repeat="song in songList track by $index">
   					<img ng-src="{{slc.thumbnail(song.link)}}" class="md-avatar" alt="{{song.link}}" />
    				<div flex class="md-list-item-text" layout="column">
    					<h3><a ng-href="{{song.link}}" target="_blank">{{song.songName}}</a></h3>
    					<p>Rating : {{song.rating == 0 ? "unrated" : song.rating}}</p>
    				</div>
    				<div>
    					<md-button ng-if="slc.allowPlay(song)" class="md-icon-button md-secondary" ng-click="slc.playSong(song)">
    						<md-icon class="md-icon-button" md-svg-icon="<c:url value="/static/images/ic_play_circle_filled_black_24px.svg" />" arial-label="play"></md-icon>
    					</md-button>
    					<md-button class="md-icon-button md-secondary" ng-click="slc.showConfirm($event, song)">
    						<md-icon class="md-icon-button" md-svg-icon="<c:url value="/static/images/ic_delete_black_24px.svg" />" aria-label="delete"/> 
    					</md-button>
    					<md-button class="md-icon-button md-secondary" ng-click="slc.sendMessage(song)">
    						<md-icon md-svg-icon="<c:url value="/static/images/ic_send_black_48px.svg" />" aria-label="send"></md-icon>
    					</md-button>
    				</div>
    				<md-divider ng-if="!$last"></md-divider>
   				</md-list-item>
   			</md-list>
   		</md-content>
	</div>
	 <div flex="5"></div>
     <div ng-if="!slc.screen_size">
     	<ng-youtube-embed
            video="slc.video_url" 
            autoplay="false"
            color="red"
            disablekb="false">
        </ng-youtube-embed>
	</div>
	
</div>

