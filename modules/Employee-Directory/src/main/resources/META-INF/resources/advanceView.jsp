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
<style>
.dataTables_filter{display:none;}
</style>
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

String pattern="dd-MM-yyyy";
SimpleDateFormat ft = new SimpleDateFormat();
ft.applyPattern(pattern);
%>

<script type="text/javascript">
function filterGlobal () {
    $('#example').DataTable().search(
        $('#global_filter').val(),
        $('#global_smart').prop('checked')
    ).draw();
}
 
function filterColumn ( i ) {
    $('#example').DataTable().column( i ).search(
        $('#col'+i+'_filter').val(),
        $('#col'+i+'_smart').prop('checked')
    ).draw();
}
 
$(document).ready(function() {
    $('#example').DataTable();
 
    $('input.global_filter').on( 'keyup click', function () {
        filterGlobal();
    } );
 
    $('input.column_filter').on( 'keyup click', function () {
        filterColumn( $(this).parents('div').attr('data-column') );
    } );
} );
</script>

       
            
            <div id="filter_col1" data-column="0"  class="advanceFilter">
              	Name
                <input type="text" class="column_filter" id="col0_filter">
               	<input type="checkbox" class="column_filter" id="col0_smart" checked="checked" style="display:none;">
            </div>
            <div id="filter_col2" data-column="1" class="advanceFilter">
              Email
                <input type="text" class="column_filter" id="col1_filter">
                <input type="checkbox" class="column_filter" id="col1_smart" checked="checked" style="display:none;">
            </div>
            <div id="filter_col3" data-column="2" class="advanceFilter">
               Designation
             	 <input type="text" class="column_filter" id="col2_filter">
               	 <input type="checkbox" class="column_filter" id="col2_smart" checked="checked" style="display:none;">
            </div>
            <div id="filter_col4" data-column="3" class="advanceFilter">
               	Contact
                <input type="text" class="column_filter" id="col3_filter">
                <input type="checkbox" class="column_filter" id="col3_smart" checked="checked" style="display:none;">
            </div>

            
      
   
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
			
	
	 <td><a href="<%=fullProfile.toString()%>"><%=user1.getFirstName().concat(" ").concat(user1.getLastName())%></a></td>
	 <td><a href="mailto:<%=user1.getEmailAddress()%>"><%=user1.getEmailAddress()%></a></td>
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
 