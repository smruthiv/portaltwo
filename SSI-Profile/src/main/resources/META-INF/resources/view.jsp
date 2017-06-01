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
<liferay-portlet:actionURL var="actionUrl" />
<aui:form action="<%=actionUrl%>" method="post" name="configurationFm"
	class="form-horizontal" role="form" enctype="multipart/form-data">
	<div class="container">
		<div class="row">
			<!-- left column -->
			<div class="col-md-3">
				<div class="text-center">
					<img src="<%=objUser.getPortraitURL(themeDisplay)%>"
						class="avatar img-circle" alt="avatar">
						<br>
					<button class="btn btn-primary btn-lg" type="button" data-target="#lexSmallModal" data-toggle="modal" >Change Picture</button>
					<aui:input type="file" id="file-input" name="userPic" label="Select Picture To Change Profile picture" style="display:none;">
					<aui:validator name="acceptFiles">'jpg,png,tif,gif'</aui:validator>
					</aui:input>
					
				</div>
				
				<div class="text-center">
				<%for(UserGroup userGroup : userGroups){if(userGroup.getName().equals("Personal_Docs")){
					String url = "/user/".concat(objUser.getScreenName()).concat("/~/51801/home");
				%>
						
			<div class="card card-horizontal taglib-horizontal-card ">
	<div class="card-row card-row-padded selectable">
		<div class="card-col-field">
			<div class="sticker sticker-default sticker-lg sticker-static">
			<span class="text-default">
			<svg class="lexicon-icon lexicon-icon-folder" role="img" title="" viewBox="0 0 512 512">
		 <path class="lexicon-icon-body" fill="none" d="M455.1,128H352c-9.5,0-31.9-45.7-38.6-59.5C298,36.4,280.4,0,245.1,0H55.9C25.1,0,0,26.9,0,59.8v395.3
	C0,486.5,25.5,512,56.9,512h398.2c31.4,0,56.9-25.5,56.9-56.9V184.9C512,153.6,486.5,128,455.1,128L455.1,128z"></path>
<path class="lexicon-icon-outline" d="M455.1,128H352c-9.5,0-31.9-45.7-38.6-59.5C298,36.4,280.4,0,245.1,0H55.9C25.1,0,0,26.9,0,59.8v395.3
	C0,486.5,25.5,512,56.9,512h398.2c31.4,0,56.9-25.5,56.9-56.9V184.9C512,153.6,486.5,128,455.1,128L455.1,128z M64,67
	c0,0,0.3-2.4,0.7-3h178.1c5.6,5.9,14.4,31.8,19.8,43.4c3.1,6.7,6.3,13.7,9.9,20.6H64L64,67L64,67z M448,448H64l0-256h384V448z"></path>
</svg> <span class="taglib-icon-label"> </span> </span> </div> </div> <div class="card-col-content card-col-gutters"> <span class="lfr-card-title-text truncate-text"> <a href="<%=url%>" title="My Documents">My Documents</a> </span> </div> </div> </div>
						
				<%}}%>
				</div>
			</div>

			<!-- edit form column -->
			<div class="col-md-9 personal-info">

				<h3 style="font-weight: bold;">Personal info</h3>


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
						value="<%=objUser.getFirstName()%>" name="lastName"
						label="Last Name">
						<aui:validator name="required" />
						<aui:validator name="alpha" />
					</aui:input>

				</div>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" readonly="true"
						value="Social Solutions International Inc" name="company" />

				</div>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" readonly="true"
						value="<%=objUser.getEmailAddress()%>" name="email" />

				</div>
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

				<h3 style="font-weight: bold;">Address</h3>

				<%if(user.getAddresses()!=null&&user.getAddresses().size()>0&&user.getAddresses().get(0)!=null){%>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text"
						value="<%=user.getAddresses().get(0).getStreet1()%>"
						name="street1" />

				</div>
				<div class="form-group col-md-6 ">
					<aui:input class="form-control" type="text"
						value="<%=user.getAddresses().get(0).getStreet2()%>"
						name="street2" />
				</div>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text"
						value="<%=user.getAddresses().get(0).getStreet3()%>"
						name="street3" />

				</div>

				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text"
						value="<%=user.getAddresses().get(0).getCity()%>" name="city" />

				</div>
				<div class="form-group col-md-6">

					<aui:select label="Country" name="country">
						<aui:option label="Select" value="0" />
						<%for(Country country : countries){ if(user.getAddresses().get(0).getCountryId()==country.getCountryId()){%>
						<aui:option label="<%=country.getName()%>"
							value="<%=country.getCountryId()%>" selected="true" />
						<%}else{%>
						<aui:option label="<%=country.getName()%>"
							value="<%=country.getCountryId()%>" />
						<%}}%>
					</aui:select>

				</div>
				<div class="form-group col-md-6">
					<aui:input class="form-control" type="text" value="" name="region" />
				</div>
				<%}else{
 %>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" value="" name="street1" />

				</div>
				<div class="form-group col-md-6 ">
					<aui:input class="form-control" type="text" value="" name="street2" />
				</div>
				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" value="" name="street3" />

				</div>

				<div class="form-group col-md-6 ">

					<aui:input class="form-control" type="text" value="" name="city" />

				</div>
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
					<aui:input class="form-control" type="text" value="" name="region" />
				</div>

				<%}%>
								<h3 style="font-weight: bold;">Password</h3>
				<div class="form-group col-md-9 ">

					<aui:input class="form-control" type="password" name="currentpassword" label="Current Password"/>

				</div>
				<div class="form-group col-md-9 ">

					<aui:input class="form-control" type="password" name="password1"
						label="New Password">
						<aui:validator name="minLength"
							errorMessage="Please enter at least eight length password">8</aui:validator>
						<aui:validator name="custom"
							errorMessage="Enter at least 1 Upper Case, 1 Lowe Case , 1 Special Character  ">
							function (val, fieldNode, ruleValue) {
							var result = false;
							if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]/.test(val)) {
							result = true;
							}
							return result;
						}
</aui:validator>
					</aui:input>

				</div>
				<div class="form-group col-md-9 ">

					<aui:input class="form-control" type="password" name="password2" label="Enter Again">
					 <aui:validator name="equalTo">'#<portlet:namespace />password1'</aui:validator>
					</aui:input>

				</div>
				<div class="form-group col-md-9">
					<input type="button" class="btn btn-primary" value="Save Changes" data-target="#lexFormModal" data-toggle="modal"/>
					<input type="submit" class="btn btn-primary" value="Save Changes" style="display:none;" id="submit-button"/>
					<input type="reset" class="btn btn-default" value="Cancel" />
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