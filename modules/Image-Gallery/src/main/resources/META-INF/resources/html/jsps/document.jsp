<%@page import="com.liferay.portal.kernel.security.permission.PermissionThreadLocal"%>
<%@page import="com.liferay.portal.kernel.security.permission.PermissionChecker"%>
<%@page import="com.liferay.portal.kernel.service.ServiceContext"%>
<%@page import="com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil"%>
<%@page import="javax.portlet.PortletSession"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ page import = "java.util.*" %>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>


<portlet:defineObjects />

<style>

	.iconFrame img {
   height: 8em;
    width: 10em;
  
    border-radius: 4px;
}

	
	.iconFrame img:hover {
    height: 8em;
    width: 10em;
    border: none;
    box-shadow: 0 0 20px rgba(67, 68, 70, 0.76);
    border-radius: 4px;
}

	
	#galleryContainer{
		float:left;
		padding-left: 1em;
		width:100%;
	}

	
	.iconFrame{
	display: inline-block;
	padding-right:1em
      }
 .galleryName {
    text-align: Center;
    width: 100%;
    font-size: 1em;
    color: #983720;
}




	#actions{
		    text-align: center;
	}
</style>


<div id="galleryContainer">
<%
ThemeDisplay themedisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
long userId = themedisplay.getUserId();
long groupId = themedisplay.getScopeGroupId();
List<DLFolder> subfolderList = new ArrayList<DLFolder>();

List<DLFolder> folderList = DLFolderLocalServiceUtil.getFolders(groupId,0);
HttpSession httpSession = request.getSession();

boolean isGalleryFolderExist = false;
for(DLFolder folder : folderList){
	if(folder.getName().equalsIgnoreCase("Image Gallery")){
		isGalleryFolderExist = true;
		subfolderList = DLFolderLocalServiceUtil.getFolders(groupId,folder.getFolderId());
		if(subfolderList.size()!=0){
			for(DLFolder eventFolder : subfolderList){
			if(!eventFolder.isInTrash()){
				List<DLFileEntry> fileEntries = DLFileEntryLocalServiceUtil.getFileEntries(groupId, eventFolder.getFolderId());
				String url = "";
				if(fileEntries.size()!=0){
					DLFileEntry file = fileEntries.get(0);
					url = themedisplay.getPortalURL() + themedisplay.getPathContext() + "/documents/" + themedisplay.getScopeGroupId() + "/" + 
							file.getFolderId() +  "/" +file.getTitle() ;
				}
				%>
				<div style="display:none">
					<portlet:renderURL var="Gallery">
		   					 <portlet:param name="jspPage" value="/html/jsps/gallery.jsp" />
							 <portlet:param name="folderId" value="<%=String.valueOf(eventFolder.getFolderId())%>" />
					</portlet:renderURL>
				</div>
				<div class = "iconFrame">
					 <aui:a href="<%= Gallery %>">
						<img src="<%=url%>" id="<%=eventFolder.getFolderId()%>" name="test" title = "<%=eventFolder.getName()%>">
						<div class="galleryName"><%=eventFolder.getName()%></div>
					 </aui:a>
				</div> 
				<%
			}
			}
		}
	}
}

if(isGalleryFolderExist==false){
		DLFolder dlforler = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, false, 0, "Image Gallery", "Image Gallery Root Folder",false, new ServiceContext());
}
%>
</div>
	<portlet:renderURL var="addImages">
		   					 <portlet:param name="jspPage" value="/html/jsps/addImages.jsp" />
	</portlet:renderURL>
	<portlet:renderURL var="addImagesToOldEvents">
		   					 <portlet:param name="jspPage" value="/html/jsps/editEventsFolder.jsp" />
	</portlet:renderURL>
<hr>
<%
PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
if(permissionChecker.isOmniadmin()){ %>
<div id = "createDoc">
	<div id="actions">
		 <aui:a href="<%= addImages %>"> <button  class = "btn btn-primary" type="button" >Create a New Event</button></aui:a>
		 <aui:a href="<%= addImagesToOldEvents %>"><button  class = "btn btn-primary" type="button" >Upload Photos to Existing Event</button></aui:a>
	 </div>
</div>
<%}%>