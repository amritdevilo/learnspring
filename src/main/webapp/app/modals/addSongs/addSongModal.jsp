<md-dialog aria="add song">
	<md-toolbar>
		<div class="md-toolbar-tools">
			<h2>Add Song</h2>
		</div>
	</md-toolbar>
	<md-dialog-content>
		<div class="md-padding" id="inputDiv" layout="column" >
			<md-input-container>
				<label>Song Name</label>
				<input id="name" name="name" ng-model="song.name" autofocus="autofocus"/>
			</md-input-container>
			<md-input-container>
				<label>Link</label>
				<input id="link" name="link" ng-model="song.link"/>
			</md-input-container>
			<div class="md-dialog-content">
			<md-slider-container>
				<span>Rating</span>
				<md-slider flex min="0" max="5" step="0.5" ng-model="song.rating"></md-slider>
			</md-slider-container>
			<div style="text-align: center">{{song.rating}}</div>
		</div>
		</div>
	</md-dialog-content>
	
	<md-dialog-actions layout="end center">
		<md-button ng-click="asc.doSubmit()" md-autofocus>Add Song</md-button>
		<md-button ng-click="asc.closeDialog()">Cancel</md-button>
	</md-dialog-actions>
</md-dialog>