<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>

<%@ include file="/init.jsp" %>
<%
int noOfErrors = SessionErrors.size(renderRequest);
if(noOfErrors>0){
%>
<script>
	 $('#<portlet:namespace/>loginusername').val("");
	 $('#<portlet:namespace/>loginpassword').val("");
</script>
<%}%>
<%String emailImageURL = themeDisplay.getPathThemeImages() + "/icons/user_email.png";%>
<%String passwordImageURL = themeDisplay.getPathThemeImages() + "/icons/password.png";%>
<%String headerImageURL = themeDisplay.getPathThemeImages() + "/sign_in_header.PNG";%>

<style>
.policy{
display:block !important;
}


</style>
<div id="signIn">
<div id="signIn_header"><img src="<%=headerImageURL%>"/></div>
<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
	
		<%
		HttpServletResponse res = PortalUtil.getHttpServletResponse(renderResponse);
		res.sendRedirect("/c/portal/logout");
		String signedInAs = HtmlUtil.escape(user.getFullName());
		if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
			String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

			signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
		}
		%>

		<liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" />
	</c:when>
	<c:otherwise>

		<%
		String formName = "loginForm";

		if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
			formName += "Modal";
		}

		String redirect = ParamUtil.getString(request, "redirect");

		String login = LoginUtil.getLogin(request, "login", company);
		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}
		%>

		<portlet:actionURL name="/login/login" secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="loginURL">
			<portlet:param name="mvcRenderCommandName" value="/login/login" />
		</portlet:actionURL>

		<aui:form action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' cssClass="sign-in-form" method="post" name="<%= formName %>" onSubmit="event.preventDefault();">
			<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="doActionAfterLogin" type="hidden" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />

			<div class="inline-alert-container lfr-alert-container"></div>

			<c:choose>
				<c:when test='<%= SessionMessages.contains(request, "passwordSent") %>'>

					<div class="alert alert-success">
						<liferay-ui:message key="Password reset link was sent to the provided email address" />
					</div>
				</c:when>
				<c:when test='<%= SessionMessages.contains(request, "userAdded") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
					String userPassword = (String)SessionMessages.get(request, "userAddedPassword");
					%>

					<div class="alert alert-success">
						<c:choose>
							<c:when test="<%= company.isStrangersVerify() || Validator.isNull(userPassword) %>">
								<liferay-ui:message key="thank-you-for-creating-an-account" />

								<c:if test="<%= company.isStrangersVerify() %>">
									<liferay-ui:message arguments="<%= userEmailAddress %>" key="your-email-verification-code-was-sent-to-x" translateArguments="<%= false %>" />
								</c:if>
							</c:when>
							<c:otherwise>
								<liferay-ui:message arguments="<%= userPassword %>" key="thank-you-for-creating-an-account.-your-password-is-x" translateArguments="<%= false %>" />
							</c:otherwise>
						</c:choose>

						<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
							<liferay-ui:message arguments="<%= userEmailAddress %>" key="your-password-was-sent-to-x" translateArguments="<%= false %>" />
						</c:if>
					</div>
				</c:when>
				<c:when test='<%= SessionMessages.contains(request, "userPending") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "userPending");
					%>

					<div class="alert alert-success">
						<liferay-ui:message arguments="<%= userEmailAddress %>" key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved" translateArguments="<%= false %>" />
					</div>
				</c:when>
			</c:choose>

			<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
			<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
			<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
			<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
			<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />

			<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>">

				<%
				UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout)errorException;
				%>

				<liferay-ui:message arguments="<%= ule.user.getUnlockDate() %>" key="this-account-is-locked" translateArguments="<%= false %>" />
			</liferay-ui:error>

			<liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= UserScreenNameException.MustNotBeNull.class %>" message="the-screen-name-cannot-be-blank" />

			<aui:fieldset>

				<%
				String loginLabel = null;

				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					loginLabel = "email-address";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					loginLabel = "screen-name";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					loginLabel = "id";
				}
				%>

			<aui:input id="loginusername" autoFocus="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) || windowState.equals(WindowState.MAXIMIZED) %>"  name="login" showRequiredLabel="<%= false %>" type="text" value="<%= login %>"  placeholder="Email Address" label=" ">
					<aui:validator name="required" errorMessage="Please enter Email Address"/>
				</aui:input>

				<aui:input id="loginpassword" name="password" showRequiredLabel="<%= false %>" type="password" value="<%= password %>" placeholder="Password" label="">
					<aui:validator name="required" errorMessage="Please enter Password"/>
				</aui:input>

				<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>

				<%-- <c:if test="<%= company.isAutoLogin() && !PropsValues.SESSION_DISABLED %>">
					<aui:input checked="<%= rememberMe %>" name="rememberMe" type="checkbox" />
				</c:if> --%>
			</aui:fieldset>

			<aui:button-row>
				<aui:button cssClass="btn-lg" type="submit" value="sign-in" />
			</aui:button-row>
			
		</aui:form>

		<liferay-util:include page="/navigation.jsp" servletContext="<%= application %>" />
		<div id="signInfooter">� Copyright 2017 SSi Inc. All rights reserved.</div>
		<aui:script sandbox="<%= true %>">
			var form = AUI.$(document.<portlet:namespace /><%= formName %>);

			form.on(
				'submit',
				function(event) {
					<c:if test="<%= Validator.isNotNull(redirect) %>">
						var redirect = form.fm('redirect');

						if (redirect) {
							var redirectVal = redirect.val();

							redirect.val(redirectVal + window.location.hash);
						}
					</c:if>

					submitForm(form);
				}
			);

			form.fm('password').on(
				'keypress',
				function(event) {
					Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>

</div>