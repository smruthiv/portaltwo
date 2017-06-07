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

<script type="text/javascript" src="/documents/20147/40002/jquery.dataTables.min.js/3097edbb-f7e2-58c7-939f-e6f07a214d11"></script>
<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet" />
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />
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
<div id="directory">
<%if(userGroupId == null || (userGroupId !=null && userGroupId.equals("0"))){ %>
<div class="alert alert-warning confmsg" role="alert">
  <strong>Configuration:-</strong> Please select User Group from configuration to display users list.
</div>
<%}else{ %>
<table id="example" class="table table-striped table-bordered" >
        <thead>
            <tr>
           		
                    
                <%if(permissionChecker.isOmniadmin()){ %>
  <th>Docs</th>	
<%}%>			
            </tr>
        </thead>

        <tbody>
           
               

  <%for(User user1:users){ %>
  <%if(user1.isActive()){ %>
    <tr>	

	<%
		String url = "/user/".concat(user1.getScreenName()).concat("/~/51801/home");
	%>

<%if(permissionChecker.isOmniadmin()){ %>
<td>

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
</svg> <span class="taglib-icon-label"> </span> </span> </div> </div> <div class="card-col-content card-col-gutters"> <span class="lfr-card-title-text truncate-text"> <a href="<%=url%>" title="Users Documents"><%=user1.getFirstName().concat(" ").concat(user1.getLastName()).concat("'s Documents")%></a> </span> </div> </div> </div>
</td>
<%}%>
</tr>
<%}}%>
 
   </tbody>
    </table>
    <%}%>
     </div>
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