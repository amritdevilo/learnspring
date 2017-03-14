<md-dialog aria-label="send message">
	<md-toolbar>
		<div class="md-toolbar-tools">
			<h2>Send Message</h2>
		</div>
	</md-toolbar>
	<md-dialog-content>
<!-- 	style="width:400px;" -->
		<div class="md-dialog-content" layout-padding layout="column" > 
			<md-autocomplete
			  md-no-cache="false"
	          md-selected-item="smc.toId"
	          md-search-text="smc.searchText"
	          md-search-text-change="smc.queryUsersList(smc.searchText)"
	          md-items="user in smc.users"
	          md-item-text="smc.constructName(user)"
	          md-min-length="1"
	          placeholder="Search for users">
		        <md-item-template layout="column">
			        <span class="item-title">
		            	<span> {{smc.constructName(user)}} </span>
		          	</span>
		          	<span class="item-metadata">
		            	<span>
		              		<strong>{{user.email}}</strong>
		            	</span>
		          	</span>
		        </md-item-template>
		        <md-not-found>
		          No user found
		        </md-not-found>
	      	</md-autocomplete>
	      </div>
	      
	      <div flex>
	      </div>
	</md-dialog-content>
		
	<md-dialog-actions layout="end center">
		<md-button ng-click="smc.doSubmit()" md-autofocus>Send Message</md-button>
		<md-button ng-click="smc.closeDialog()">Cancel</md-button>
	</md-dialog-actions>
</md-dialog>