<%@page import="com.liferay.portal.kernel.security.permission.PermissionChecker"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.model.UserGroup"%>
<%@page import="com.liferay.portal.kernel.security.auth.CompanyThreadLocal"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@ include file="/init.jsp" %>

<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet" />
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />
	<script>
	$(document).ready(function() {

    $('#example').dataTable( {
    	 "order": [],
        "aoColumnDefs": [
                         
            { 'bSortable': false, 'aTargets': [ 0 ] }
         ]
  });

} );
</script>
<%


Long companyId = CompanyThreadLocal.getCompanyId();
String userGroupId = portletPreferences.getValue("userGroupId", "0");
UserGroup userGroup = null;
if(userGroupId!=null && !userGroupId.isEmpty() && !userGroupId.equals("0") ){
	 userGroup = UserGroupLocalServiceUtil.getUserGroup(Long.valueOf(userGroupId));
}
List<User> users = new ArrayList<User>();
if(userGroup!=null){
	 users = UserLocalServiceUtil.getUserGroupUsers(userGroup.getUserGroupId());
}

%>

    <portlet:renderURL var="advanceView" >
				<portlet:param name="jspPage" value="/advanceView.jsp"/>
				
			</portlet:renderURL>
<div  id="userDocs">
<%if(userGroupId == null || (userGroupId !=null && userGroupId.equals("0"))){ %>
<div class="alert alert-warning confmsg" role="alert">
  <strong>Configuration:-</strong> Please select User Group from configuration to display users list.
</div>
<%}else{ %>

<a href="<%=advanceView.toString()%>" class="advanceSearch">Advanced Search</a>
<table id="example" class="table table-striped table-bordered" >
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Designation</th>
                <th>Contact</th>
                
            </tr>
        </thead>

        <tbody>
           
               

  <%for(User user1:users){ %>
  <%if(user1.isActive()){ %>
    <tr>
    <portlet:renderURL var="fullProfile" >
				<portlet:param name="jspPage" value="/fullProfile.jsp"/>
				<portlet:param name="editId" value="<%=String.valueOf(user1.getUserId())%>"/>
			</portlet:renderURL>
			
	
	 <td><a style="color: rgb(109, 38, 22);" href="<%=fullProfile.toString()%>"><%=user1.getFirstName().concat(" ").concat(user1.getLastName())%></a></td>
	 <td><a style="color: rgb(109, 38, 22);" href="mailto:<%=user1.getEmailAddress()%>"><%=user1.getEmailAddress()%></a></td>
	<td><%=user1.getJobTitle() %></td>
	  
<%if(user1.getPhones()!=null && user1.getPhones().size() > 0 && user1.getPhones().get(0) !=null){ %>
<td><%= user1.getPhones().get(0).getNumber() %></td>
<%}else{%>
<td></td>
<%}%>

</tr>
<%}}%>



   </tbody>
    </table>
    
    <%}%>
    </div>
 