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
			<md-input-container>
				<label>Resource</label>
				<input id="resource" name="resource" ng-model="song.resource" />
			</md-input-container>
		</div>
	</md-dialog-content>
	
	<md-dialog-actions layout="end center">
		<md-button ng-click="asc.doSubmit()" md-autofocus>Add Song</md-button>
		<md-button ng-click="asc.closeDialog()">Cancel</md-button>
	</md-dialog-actions>
</md-dialog>