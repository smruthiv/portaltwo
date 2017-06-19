<%@page import="javax.portlet.RenderRequest"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>

<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<style>
.my{
margin:5%;
}
</style>
<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<%
String userGroupId = portletPreferences.getValue("userGroupId", "0");
List<UserGroup> users = UserGroupLocalServiceUtil.getUserGroups(0, UserGroupLocalServiceUtil.getUserGroupsCount());
%>


<aui:form action="<%= configurationActionURL %>" method="post" name="configurationFm" >
<div class="my">
	<aui:select label="User Group" name="userGroup" >
	<%if(userGroupId.equals("0")){ %>
						<aui:option label="Select" value="0" selected="true" />
	<%}else{ %>
						<aui:option label="Select" value="0"  />
	<%} %>
	
						<%for(UserGroup userGroup : users){ %>
					<%if(userGroupId.equals(String.valueOf(userGroup.getUserGroupId()))){ %>	
						<aui:option label="<%=userGroup.getName()%>"
							value="<%=userGroup.getUserGroupId()%>" selected="true"/>
							<%}else{ %>
							<aui:option label="<%=userGroup.getName()%>"
							value="<%=userGroup.getUserGroupId()%>" />
							<%} %>
					<%}%>
	</aui:select>

        <aui:button type="submit"></aui:button>

 </div>
</aui:form>

