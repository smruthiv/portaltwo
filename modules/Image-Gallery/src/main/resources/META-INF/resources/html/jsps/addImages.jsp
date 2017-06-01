<%@page import="com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ page import = "java.util.*" %>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>

<portlet:defineObjects />
<%
	String newFolderId = request.getParameter("newFolderId"); 
	String newFolderName = request.getParameter("newFolderName");
	System.out.println("new folder id " + newFolderId); 

%>
<style>
	.form{
		    display: inline-block;
    		padding: 1em;
    		width:47%;
	}
</style>

<portlet:actionURL  var="uploadImagesURL" name = "uploadImages">
</portlet:actionURL>
<portlet:actionURL  var="createDocumentURL" name = "createDocument">
</portlet:actionURL>
<portlet:actionURL  var="uploadImagesOldFolderURL" name = "uploadImagesOldFolder">
</portlet:actionURL>





<%
ThemeDisplay themedisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
long userId = themedisplay.getUserId();
long groupId = themedisplay.getScopeGroupId();
List<DLFolder> subfolderList = new ArrayList<DLFolder>();
List<DLFolder> folderList = DLFolderLocalServiceUtil.getFolders(groupId,0);
	HttpSession httpSession = request.getSession();


boolean isGalleryFolderExist = false;
//System.out.println(folderList.size());
for(DLFolder folder : folderList){
	//System.out.println("folde name :::: " + folder.getName());
	if(folder.getName().equalsIgnoreCase("ImageGalary")){
		isGalleryFolderExist = true;
		//System.out.println(" galary folder already existed ");
		subfolderList = DLFolderLocalServiceUtil.getFolders(groupId,folder.getFolderId());
		//System.out.println(" subfolder list " + subfolderList.size());
		if(subfolderList.size()!=0){
			for(DLFolder eventFolder : subfolderList){
			//	System.out.println("subfolder name :::: " + eventFolder.getName());
				List<DLFileEntry> fileEntries = DLFileEntryLocalServiceUtil.getFileEntries(groupId, eventFolder.getFolderId());
				String url = "";
				if(fileEntries.size()!=0){
					DLFileEntry file = fileEntries.get(0);
					url = themedisplay.getPortalURL() + themedisplay.getPathContext() + "/documents/" + themedisplay.getScopeGroupId() + "/" + 
							file.getFolderId() +  "/" +file.getTitle() ;
	        	 	// System.out.println("DL Link=>"+url);
				}
				
			}
		}
	}
}
%>

	<div id = "createDoc">
		<%
			if(newFolderId!="" && newFolderId!= null){
		%>
		
		<aui:form action="<%=uploadImagesURL %>"  enctype="multipart/form-data" method="post" >
			 <aui:input name="upload_images" type = "file" required="true" multiple="<%=true %>" ></aui:input> 
			<aui:input name="newlyCreatedFolder" value="<%=newFolderId%>" type="hidden"></aui:input>
			<p><%=newFolderName%></p>
			<aui:button name="create" type="submit" value = "upload"></aui:button> 
		</aui:form>
	
		
		<%}%>
			
		<%
			if(newFolderId =="" || newFolderId == null){
		%>
		<aui:form action="<%=createDocumentURL %>" cssClass="forms">
			<span class="heading"><b>Create a New Event Folder..</b></span>
			<br><br>
			<span>
			<aui:input name="folderName" type = "text" placeholder="event name" required="true" label="Folder Name"></aui:input>
			<aui:input name="folderDesc" type = "text" placeholder="event description" label="Event Description"></aui:input>
			<aui:button name="create" type="submit" value="Create Event Folder"></aui:button>
			</span>
		</aui:form>
			<%}%>
	</div>