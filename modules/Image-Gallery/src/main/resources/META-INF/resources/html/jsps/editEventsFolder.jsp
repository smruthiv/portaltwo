<%@page import="com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
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
	System.out.println("new folder id " + newFolderId); 

%>
<style>
	.iconFrame img{
	height: 10%;
    width: 61%;
    border: 1px solid #000000;
    border-radius: 4px;
	}
	
	.iconFrame img:HOVER{
		height: 10%;
	    width: 61%;
	   	border: 1px solid red;
		box-shadow: 0 0 20px rgba(18, 47, 208, 0.76);
		border-radius: 4px; 
	}
	
	#galleryContainer{
		float:left;
		padding-left: 1em;
		width:100%;
	}

	.form{
		    display: inline-block;
    		padding: 1em;
    		width:47%;
	}
	.iconFrame{
	display: inline-block;
    width: 19%;
    }
    
    .galleryName{
    text-align: Center;
    width: 61%;}
    
    /* .heading{
    	background-color: red;
    } */
    
   
</style>

<portlet:actionURL  var="uploadImagesURL" name = "uploadImages">
</portlet:actionURL>
<portlet:actionURL  var="createDocumentURL" name = "createDocument">
</portlet:actionURL>
<portlet:actionURL  var="uploadImagesOldFolderURL" name = "uploadImagesOldFolder">
</portlet:actionURL>
<%-- 


	<aui:form action="<%=uploadImagesURL %>"  enctype="multipart/form-data" method="post" >
		 <aui:input name="upload_images" type = "file" multiple="<%=true %>" ></aui:input> 
		<aui:input name="newlyCreatedFolder" value="<%=newFolderId%>" type="text"></aui:input>
		<aui:button name="create" type="submit" value = "upload"></aui:button> 
	</aui:form> --%>
	


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
		System.out.println(" galary folder already existed ");
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
		
<%-- 		<aui:form action="<%=createDocumentURL %>" cssClass="forms">
			<span class="heading"><b>Create a New Event Folder..</b></span>
			<br><br>
			<span>
			<aui:input name="folderName" type = "text" placeholder="event name" required="true" label="Folder Name"></aui:input>
			<aui:input name="folderDesc" type = "text" placeholder="event description" label="Event Description"></aui:input>
			<aui:button name="create" type="submit" value="Create Event Folder"></aui:button>
			</span>
		</aui:form>
		 --%>
		<aui:form action="<%=uploadImagesOldFolderURL %>"  enctype="multipart/form-data" method="post" cssClass="forms">
			<span class="heading"><b>Upload Images in already existing Event Folder..</b></span>
			<br><br>
			<span>
			<aui:select name="eventFolder" id="eventFolder" label="Event Folders" required="true">
			<aui:option value="select">--Select--</aui:option>
				<%int size=subfolderList.size();
				for(int i=0;i<size;i++){%>
				<aui:option value="<%=subfolderList.get(i).getFolderId()%>" ><%=subfolderList.get(i).getName()%></aui:option>
				<%}%>
			</aui:select>
			 <aui:input name="upload_images" label="Upload Images" type = "file" required="true" multiple="<%=true%>" ></aui:input>
			 <aui:button name="create" type="submit" value = "Upload Images"></aui:button> 
			 </span> 
		</aui:form>
	</div>