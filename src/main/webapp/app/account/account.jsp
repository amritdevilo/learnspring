<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div layout="row">
	<div flex="5"></div>
	<div ng-cloak flex="{{ac.getFormFlexValue()}}">
   		<div>
   			<md-content layout-padding layout="column">
   				<md-input-container>
   					<label>First Name</label>
   					<input ng-model="client.firstName" ng-disabled="true">
   				</md-input-container>
   				
   				<md-input-container>
   					<label>Last Name</label>
   					<input ng-model="client.lastName" ng-disabled="true">
   				</md-input-container>
   				
   				<md-input-container>
   					<label>Email</label>
   					<input ng-model="client.email" ng-disabled="true">
   				</md-input-container>
   				
   				<md-input-container>
   					<label>Old Password</label>
   					<input ng-model="oldPasswd" type="password" ng-pattern="ac.passwordRegEx">
   				</md-input-container>
   				
   				<md-input-container>
   					<label>New Password</label>
   					<input required name="newPasswd" ng-model="newPasswd" type="password" ng-pattern="ac.passwordRegEx">
   				</md-input-container>
   				
   				<md-input-container>
   					<label>Confirm Password</label>
   					<input required name="confPasswd" ng-model="confPasswd" type="password" ng-pattern="ac.passwordRegEx">
   				</md-input-container>
   				
   				<md-button class="md-raised md-primary" 
   					ng-disabled="newPasswd == undefined || confPasswd == undefined || newPasswd!=confPasswd" ng-click="ac.updateClient()">
   					Update
   				</md-button>
   			</md-content>
   		</div>
	</div>
</div>
