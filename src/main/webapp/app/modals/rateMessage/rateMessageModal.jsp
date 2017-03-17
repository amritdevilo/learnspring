<md-dialog aria-label="rate message">
	<md-toolbar class="md-toolbar-tools">
		<p flex>Import Song</p>
		<md-button class="md-icon-button md-secondary" ng-click="rmc.cancelDialog()">
			<md-icon md-svg-icon="<c:url value="/static/images/ic_clear_black_24px.svg" />" aria-label="close"></md-icon>
		</md-button>
	</md-toolbar>
	
	<md-dialog-content>
		<div class="md-dialog-content">
			<md-slider-container>
				<span>Rating</span>
				<md-slider flex min="0" max="5" step="0.5" ng-model="rating"></md-slider>
			</md-slider-container>
			<div style="text-align: center">{{rating}}</div>
		</div>
	</md-dialog-content>
	
	<md-dialog-actions layout="row">
		<md-button ng-click="rmc.closeDialog()">Import</md-button>
	</md-dialog-actions>
</md-dialog>