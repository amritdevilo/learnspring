<md-dialog aria="send message">
	<md-toolbar>
		<div class="md-toolbar-tools">
			<h2>Send Message</h2>
		</div>
	</md-toolbar>
	<md-dialog-content>
<!-- 		<md-input-container> -->
<!-- 			<label>user</label> -->
<!-- 			<md-select name="user" ng-model="toId" required> -->
<!-- 				<md-option ng-repeat="user in users" value="user.userId"> -->
<!-- 					<div> -->
<!-- 						<h3>{{user.firstName + " " + user.lastName}}</h3><br> -->
<!-- 						<p>user.email</p> -->
<!-- 					</div> -->
<!-- 				</md-option> -->
<!-- 				<md-option ng-if="!user.length" value="">No results found</md-option> -->
<!-- 			</md-select> -->
<!-- 		</md-input-container> -->

		<md-autocomplete
          md-selected-item="toId"
          md-search-text="key"
          md-search-text-change="smc.searchTextChange(key)"
          md-items="user in smc.queryUserList(key)"
          md-item-text="smc.constructName(user)"
          md-min-length="1"
          placeholder="Search for users">
<!--         <md-item-template> -->
<!--           <span md-highlight-text="ctrl.searchText" md-highlight-flags="^i">{{item.display}}</span> -->
<!--         </md-item-template> -->
      </md-autocomplete>
	</md-dialog-content>
		
	<md-dialog-actions layout="end center">
		<md-button ng-click="smc.doSubmit()" md-autofocus>Send Message</md-button>
		<md-button ng-click="smc.closeDialog()">Cancel</md-button>
	</md-dialog-actions>
</md-dialog>