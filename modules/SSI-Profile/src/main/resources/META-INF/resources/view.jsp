<%@page import="com.liferay.portal.kernel.service.AddressLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Address"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.portal.kernel.service.CountryServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.model.Country"%>
<%@ include file="/init.jsp"%>
<%

ThemeDisplay themeDisplay1 = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
User objUser=themeDisplay1.getUser(); 
List<Country> countries = CountryServiceUtil.getCountries();
List<UserGroup> userGroups = objUser.getUserGroups();
boolean showMyDocs = false;
%>

<liferay-ui:error key="new-password-cant-be-same-as-old-password" message="Your new password cannot be the same as your old password. Please enter a different password."/>
<liferay-ui:error key="password-startwith-space" message="Password should not start or end with space."/>
<liferay-ui:error key="invalid-current-password" message="Invalid Current Password"/>
<liferay-ui:error key="confirm-new-password" message="New password and confirm password should be same"/>
<liferay-ui:error key="name-is-required" message="Current password required to reset new password."></liferay-ui:error>
<liferay-ui:error key="street1-required" message="Please enter a valid street1."></liferay-ui:error>
<liferay-ui:error key="city-required" message="Please enter a valid city."></liferay-ui:error>
<liferay-ui:error key="street1-city-required" message="City required fields."></liferay-ui:error>
<liferay-ui:success key="success" message="Profile saved successfully!"/>

<liferay-portlet:actionURL var="actionUrl" />
<aui:form action="<%=actionUrl%>" method="post" name="configurationFm"
	class="form-horizontal" role="form" enctype="multipart/form-data">
	<div id="myProfile">
	<div class="container">
		<div class="row">
			<!-- left column -->
			<div class="col-md-3">
				<div class="text-center">
				<%User myprouser = UserLocalServiceUtil.getUser(objUser.getUserId()); 
				  String srcImg = myprouser.getPortraitURL(themeDisplay);	
				%>
					<img src="<%=srcImg%>"
						class="avatar img-circle" alt="avatar">
						<br>
					<button class="btn btn-primary btn-lg" type="button" data-target="#lexSmallModal" data-toggle="modal" >Change Picture</button>
					<aui:input type="file" id="file-input" name="userPic" label="Select Picture To Change Profile picture" style="display:none;">
					<aui:validator name="acceptFiles">'jpg,png,tif,gif'</aui:validator>
					</aui:input>
					
				</div>
				
				
			</div>

			<!-- edit form column -->
			<div class="col-md-9 personal-info">

				<h3 style="font-weight: bold;">Personal info</h3>

<aui:row>
				<div class="form-group col-md-6 ">


					<aui:input class="form-control" type="text"
						value="<%=objUser.getFirstName()%>" name="firstName"
						label="First Name">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>

				</div>
					<div class="form-group col-md-6 ">


					<aui:input class="form-control" type="text"
						value="<%=objUser.getLastName()%>" name="lastName"
						label="Last Name">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>

				</div>
	</aui:row>	
	<aui:row>		
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" readonly="true"
						value="Social Solutions International Inc" name="company" />

				</div>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" readonly="true"
						value="<%=objUser.getEmailAddress()%>" name="email" />

				</div>
	</aui:row>		
	<aui:row>		
				<div class="form-group col-md-6 ">
					<%if(objUser.getPhones()!=null && objUser.getPhones().size()>0 && objUser.getPhones().get(0)!=null){%>
					<%String phoneNumber = objUser.getPhones().get(0).getNumber();  %>
					<aui:input class="form-control" type="text"
						value="<%=phoneNumber%>" name="phoneNumber" />
					<%}else{%>
					<aui:input class="form-control" type="text" value=""
						name="phoneNumber" />
					<%}%>
				</div>

				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text"
						value="<%=objUser.getContact().getSkypeSn()%>" name="skype" />

				</div>
</aui:row>
				<h3 style="font-weight: bold;">Address</h3>
				

				<%if(objUser.getAddresses()!=null&&objUser.getAddresses().size()>0&&objUser.getAddresses().get(0)!=null){
				%>
	<aui:row>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text"
						value="<%=objUser.getAddresses().get(0).getStreet1()%>"
						name="street1" id="street1" />

				</div>
				<div class="form-group col-md-6 ">
					<aui:input class="form-control" type="text"
						value="<%=objUser.getAddresses().get(0).getStreet2()%>"
						name="street2" />
				</div>
	</aui:row>
	<aui:row>			
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text"
						value="<%=objUser.getAddresses().get(0).getStreet3()%>"
						name="street3" />

				</div>

				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text"
						value="<%=objUser.getAddresses().get(0).getCity()%>" name="city" >
						</aui:input>

				</div>
	</aui:row>			
	<aui:row>
				<div class="form-group col-md-6">

					<aui:select label="Country" name="country">
						<aui:option label="Select" value="0" />
						<%for(Country country : countries){ if(objUser.getAddresses().get(0).getCountryId()==country.getCountryId()){%>
						<aui:option label="<%=country.getName()%>"
							value="<%=country.getCountryId()%>" selected="true" />
						<%}else{%>
						<aui:option label="<%=country.getName()%>"
							value="<%=country.getCountryId()%>" />
						<%}}%>
					</aui:select>

				</div>
				<div class="form-group col-md-6">
					<aui:input class="form-control" type="text" value="<%=objUser.getAddresses().get(0).getZip()%>" name="zipcode" />
				</div>
	</aui:row>			
				<%}else{
 %>
 <aui:row>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" value="" name="street1" />

				</div>
				<div class="form-group col-md-6 ">
					<aui:input class="form-control" type="text" value="" name="street2" />
				</div>
</aui:row>
<aui:row>				
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" value="" name="street3" />

				</div>

				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" value="" name="city" >
							
					</aui:input>
					</div>
</aui:row>		
	<aui:row>			
				<div class="form-group col-md-6">

					<aui:select label="Country" name="country">
						<aui:option label="Select" value="0" />
						<%for(Country country : countries){ %>
						<aui:option label="<%=country.getName()%>"
							value="<%=country.getCountryId()%>" />
						<%}%>
					</aui:select>

				</div>
							
					<div class="form-group col-md-6">
					<aui:input class="form-control" type="text" value="" name="zipcode" label="ZIP Code" />
				</div>

</aui:row>
				<%}%>
								<h3 style="font-weight: bold;">Password</h3>
		<aui:row>						
				<div class="form-group col-md-9 ">

					<aui:input class="form-control" type="password" name="current" label="Current Password"/>

				</div>
		</aui:row>
		<aui:row>		
				<div class="form-group col-md-9 ">

					<aui:input class="form-control" type="password" name="password1" id="password1"
						label="New Password" onkeyup="sync()">
						<aui:validator name="minLength"
							errorMessage="Please enter at least eight length password">8</aui:validator>
						<aui:validator name="custom"
							errorMessage="Enter at least 1 Upper Case, 1 Lower Case , 1 Special Character in your password.">
							function (val, fieldNode, ruleValue) {
							var result = false;
							if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]/.test(val)) {
							result = true;
							}
							return result;
						}
</aui:validator>
					</aui:input>
					<div style="display:none;">
<aui:input type="text" name="fname" style="display:none;" id="fname"/>
</div>
				</div>
				</aui:row>
				<aui:row>
				<div class="form-group col-md-9 ">

					<aui:input class="form-control" type="password" name="password2" label="Enter Again">
					 <aui:validator name="equalTo">'#<portlet:namespace />password1'</aui:validator>
					</aui:input>

				</div>
				</aui:row>
				<aui:row>
				<div class="form-group col-md-9">
					 <input type="button" class="btn btn-primary" value="Save Changes" onclick="submitFm()"/>
	 				 <input type="reset" class="btn btn-default" value="Cancel" />
				</div>
				</aui:row>

			</div>
		</div>
		</div>
		</div>
		
</aui:form>




<div aria-labelledby="lexSmallModalLabel" class="fade modal" id="lexSmallModal" role="dialog" tabindex="-1" style="display: none;">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button aria-labelledby="Close" class="btn btn-default close" data-dismiss="modal" role="button" type="button" onclick="document.getElementById('<portlet:namespace/>file-input').click();">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-times" viewBox="0 0 512 512">
								
<path class="lexicon-icon-outline" d="M295.781 256l205.205-205.205c10.998-10.998 10.998-28.814 0-39.781-10.998-10.998-28.815-10.998-39.781 0l-205.205 205.205-205.205-205.238c-10.966-10.998-28.814-10.998-39.781 0-10.998 10.998-10.998 28.814 0 39.781l205.205 205.238-205.205 205.205c-10.998 10.998-10.998 28.815 0 39.781 5.467 5.531 12.671 8.265 19.874 8.265s14.407-2.734 19.907-8.233l205.205-205.238 205.205 205.205c5.5 5.5 12.703 8.233 19.906 8.233s14.407-2.734 19.906-8.233c10.998-10.998 10.998-28.815 0-39.781l-205.238-205.205z"></path>
</svg>
						</button>

						<button class="btn btn-default modal-primary-action-button visible-xs" type="button" onclick="document.getElementById('<portlet:namespace/>file-input').click();">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-check" viewBox="0 0 512 512">
								
<path class="lexicon-icon-outline" d="M502.091 60.993c-9.909-9.91-25.962-9.91-35.843 0l-336.988 336.988-83.508-83.451c-9.881-9.909-25.962-9.909-35.843 0-9.909 9.909-9.909 25.962 0 35.843l98.257 98.257c2.608 2.608 5.679 4.433 8.924 5.679 4.028 2.464 8.403 4.115 12.952 4.115 6.49 0 12.981-2.464 17.936-7.418l354.112-354.141c9.909-9.909 9.909-25.962 0-35.871l0-0.001z"></path>
</svg>
						</button>

						<h4 class="modal-title" id="lexSmallModalLabel">Alert</h4>
					</div>

					<div class="modal-body">
						<h4>Select a picture of yourself which clearly shows your face.</h4>
					</div>

					
				</div>
			</div>
		</div>
		
		
		
		<div aria-labelledby="lexSmallModalLabel" class="fade modal" id="lexFormModal" role="dialog" tabindex="-1" style="display: none;">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button aria-labelledby="Close" class="btn btn-default close" data-dismiss="modal" role="button" type="button" onclick="document.getElementById('<portlet:namespace/>configurationFm').submit()">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-times" viewBox="0 0 512 512">
								
<path class="lexicon-icon-outline" d="M295.781 256l205.205-205.205c10.998-10.998 10.998-28.814 0-39.781-10.998-10.998-28.815-10.998-39.781 0l-205.205 205.205-205.205-205.238c-10.966-10.998-28.814-10.998-39.781 0-10.998 10.998-10.998 28.814 0 39.781l205.205 205.238-205.205 205.205c-10.998 10.998-10.998 28.815 0 39.781 5.467 5.531 12.671 8.265 19.874 8.265s14.407-2.734 19.907-8.233l205.205-205.238 205.205 205.205c5.5 5.5 12.703 8.233 19.906 8.233s14.407-2.734 19.906-8.233c10.998-10.998 10.998-28.815 0-39.781l-205.238-205.205z"></path>
</svg>
						</button>

						<button class="btn btn-default modal-primary-action-button visible-xs" type="button" onclick="document.getElementById('<portlet:namespace/>configurationFm').submit()">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-check" viewBox="0 0 512 512">
								
<path class="lexicon-icon-outline" d="M502.091 60.993c-9.909-9.91-25.962-9.91-35.843 0l-336.988 336.988-83.508-83.451c-9.881-9.909-25.962-9.909-35.843 0-9.909 9.909-9.909 25.962 0 35.843l98.257 98.257c2.608 2.608 5.679 4.433 8.924 5.679 4.028 2.464 8.403 4.115 12.952 4.115 6.49 0 12.981-2.464 17.936-7.418l354.112-354.141c9.909-9.909 9.909-25.962 0-35.871l0-0.001z"></path>
</svg>
						</button>

						<h4 class="modal-title" id="lexFormModal">Alert</h4>
					</div>

					<div class="modal-body">
						<h4>This info is updated in Portal Only and does not update HR Information in Bamboo.</h4>
					</div>

					
				</div>
			</div>
		</div>
		
		<div aria-labelledby="lexSmallModalLabel" class="fade modal" id="lexFormModal1" role="dialog" tabindex="-1" style="display: none;">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button aria-labelledby="Close" class="btn btn-default close" data-dismiss="modal" role="button" type="button" >
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-times" viewBox="0 0 512 512">
								
<path class="lexicon-icon-outline" d="M295.781 256l205.205-205.205c10.998-10.998 10.998-28.814 0-39.781-10.998-10.998-28.815-10.998-39.781 0l-205.205 205.205-205.205-205.238c-10.966-10.998-28.814-10.998-39.781 0-10.998 10.998-10.998 28.814 0 39.781l205.205 205.238-205.205 205.205c-10.998 10.998-10.998 28.815 0 39.781 5.467 5.531 12.671 8.265 19.874 8.265s14.407-2.734 19.907-8.233l205.205-205.238 205.205 205.205c5.5 5.5 12.703 8.233 19.906 8.233s14.407-2.734 19.906-8.233c10.998-10.998 10.998-28.815 0-39.781l-205.238-205.205z"></path>
</svg>
						</button>

						<button class="btn btn-default modal-primary-action-button visible-xs" type="button">
							<svg aria-hidden="true" class="lexicon-icon lexicon-icon-check" viewBox="0 0 512 512">
								
<path class="lexicon-icon-outline" d="M502.091 60.993c-9.909-9.91-25.962-9.91-35.843 0l-336.988 336.988-83.508-83.451c-9.881-9.909-25.962-9.909-35.843 0-9.909 9.909-9.909 25.962 0 35.843l98.257 98.257c2.608 2.608 5.679 4.433 8.924 5.679 4.028 2.464 8.403 4.115 12.952 4.115 6.49 0 12.981-2.464 17.936-7.418l354.112-354.141c9.909-9.909 9.909-25.962 0-35.871l0-0.001z"></path>
</svg>
						</button>

						<h4 class="modal-title" id="lexFormModal">Alert</h4>
					</div>

					<div class="modal-body">
						<h4>Your forms contains errors.</h4>
					</div>

					
				</div>
			</div>
		</div>
		<script>
$(function(){
	 $('#<portlet:namespace/>fname').val("");
});
</script>
		
<script>
function submitFm() {
	 var x = document.getElementsByClassName("form-validator-stack");
	 if(x && x.length == 0){
		 $('#lexFormModal').modal('show');
	 }else{
		 $('#lexFormModal1').modal('show');
	 } 
}
</script>

<script>
function sync()
{
  var element = document.getElementById("<portlet:namespace/>password1");
  $('#<portlet:namespace/>fname').val("a".concat(element.value).concat("a"));
}
</script>