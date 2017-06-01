package Image.Gallery.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Tushar.Patel
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Image-Gallery Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/jsps/document.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ImageGalleryPortlet extends MVCPortlet {
	public static void createDocument(ActionRequest req,ActionResponse res) throws PortalException, SystemException{
		ThemeDisplay themedisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themedisplay.getScopeGroupId();
		long userId = themedisplay.getUserId();
		List<DLFolder> folderList = DLFolderLocalServiceUtil.getFolders(groupId,0);
		String eventFolderName = ParamUtil.getString(req, "folderName");
		String eventFolderDesc = ParamUtil.getString(req, "folderDesc");
		for(DLFolder folder : folderList){
			if(folder.getName().equalsIgnoreCase("ImageGalary")){
				long folderId = folder.getFolderId();
			//DLFolder dlforler = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, false, folderId, eventFolderName, eventFolderDesc,false, new ServiceContext());
				Folder dlforler =  DLAppLocalServiceUtil.addFolder(userId, themedisplay.getScopeGroupId(), folderId, eventFolderName, eventFolderDesc, new ServiceContext());
			System.out.println(eventFolderName + " folder created successfully with id " + dlforler.getFolderId());
        	Role role = RoleLocalServiceUtil.getRole(dlforler.getCompanyId(), "Site Member");
        	System.out.println(" Role Name : " + role.getName() + " Role ID : " + role.getRoleId());
        	String[] viewAccess = {"ACCESS","VIEW"};

        	
    		
    		 
        	Folder tempEventFolder = DLAppLocalServiceUtil.getFolder(groupId, folderId, eventFolderName);
    		 
        	ResourcePermissionLocalServiceUtil.setResourcePermissions(themedisplay.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlforler.getFolderId()), role.getRoleId(), new String[]{"VIEW"});
    		 
			res.setRenderParameter("newFolderId", dlforler.getFolderId()+"");
			res.setRenderParameter("newFolderName", eventFolderName+"");
			}
			res.setRenderParameter("mvcPath", "/html/jsps/addImages.jsp");
		}
	}
	
	public static void uploadImages(ActionRequest req,ActionResponse res) throws PortalException, SystemException, IOException{
		ThemeDisplay themedisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		System.out.println("uploadImages");
		 UploadPortletRequest uploadrequest = PortalUtil.getUploadPortletRequest(req);
		 Map<String, FileItem[]> files =uploadrequest.getMultipartParameterMap();
		 System.out.println(" Multi files size  : " + files.size());
		 final FileItem[] arr = uploadrequest.getMultipartParameterMap().get("upload_images");
		 System.out.println(arr.length);
		 //File file = uploadrequest.getFile("upload_images");
		  //	System.out.println(file);
	        final long repoId = themedisplay.getScopeGroupId();
	        final long folderId = ParamUtil.getLong(uploadrequest, "newlyCreatedFolder");
	        
	        System.out.println("folderId getting back : " + folderId);
	        for (final FileItem file : arr) {
	        	InputStream fis = file.getInputStream();
	        	System.out.println("fis : " + fis + " name of file : " + file.getFileName());
	            /*FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,
	                    folderId, fi.getFileName(), fi.getContentType(), fi.getFileName(), null, null, fi.getInputStream(),
	                    fi.getSize(), new ServiceContext());*/
	        	FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,folderId,file.getFileName(), null, file.getFileName(), null, null, fis,file.getStoreLocation().getTotalSpace(), new ServiceContext());
	        	Role role = RoleLocalServiceUtil.getRole(entry.getCompanyId(), "Site Member");
	        	System.out.println(" Role Name : " + role.getName() + " Role ID : " + role.getRoleId());
	        	
	        	
	        	//setting view permission to uploading images for sitemember role.
	        	long resource = ResourceLocalServiceUtil.getResource(entry.getCompanyId(),DLFileEntry.class.getName(),ResourceConstants.SCOPE_INDIVIDUAL,String.valueOf(entry.getFileEntryId())).getResourceId();
        		String[] actionsRW = new String[]{ActionKeys.VIEW, ActionKeys.UPDATE};
        		long roleId = role.getRoleId(); /* obtain a roleId here */
        		ResourcePermission resourcePermission = null;
        		             try
        		            {
        		                resourcePermission = ResourcePermissionLocalServiceUtil.getResourcePermission(entry.getCompanyId(),
        		                        DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(entry
        		                                 .getPrimaryKey()), roleId);
        		 
        		               
        		                 if (Validator.isNotNull(resourcePermission))
        		                {
        		                 
        		                    //resourcePermission.setActionIds(actionIds);
        		                    ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);
        		                }
        		            } catch (Exception e)
        		            {
        		
        		                resourcePermission = ResourcePermissionLocalServiceUtil
        		                        .createResourcePermission(CounterLocalServiceUtil.increment());
        		               resourcePermission.setCompanyId(entry.getCompanyId());
        		                resourcePermission.setName(DLFileEntry.class.getName());
        		                resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
        		                resourcePermission.setPrimKey(String.valueOf(entry.getPrimaryKey()));
        		                resourcePermission.setRoleId(roleId);
        		                resourcePermission.setActionIds(1);// (ActionKeys.VIEW);
        		                ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);
        		            }
        		
        }
       // FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,folderId,file.getName(), null, file.getName(), null, null, fis,file.getTotalSpace(), new ServiceContext());
	}

	
	public static void uploadImagesOldFolder(ActionRequest req,ActionResponse res) throws PortalException, SystemException, IOException{
		ThemeDisplay themedisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		System.out.println("uploadImagesOldFolder");
		 UploadPortletRequest uploadrequest = PortalUtil.getUploadPortletRequest(req);
		 Map<String, FileItem[]> files =uploadrequest.getMultipartParameterMap();
		 System.out.println(" Multi files size  : " + files.size());
		 final FileItem[] arr = uploadrequest.getMultipartParameterMap().get("upload_images");
		 System.out.println(arr.length);
		 //File file = uploadrequest.getFile("upload_images");
		  //	System.out.println(file);
	        final long repoId = themedisplay.getScopeGroupId();
	        final long folderId = ParamUtil.getLong(uploadrequest, "eventFolder");
	        System.out.println(" folder id " + folderId);
	        System.out.println("folderId getting back : " + folderId);
	        for (final FileItem file : arr) {
	        	InputStream fis = file.getInputStream();
	        	//System.out.println("fis : " + fis + " name of file : " + file.getFileName());
	            /*FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,
	                    folderId, fi.getFileName(), fi.getContentType(), fi.getFileName(), null, null, fi.getInputStream(),
	                    fi.getSize(), new ServiceContext());*/
	        	FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,folderId,file.getFileName(), null, file.getFileName(), null, null, fis,file.getStoreLocation().getTotalSpace(), new ServiceContext());
	        	Role role = RoleLocalServiceUtil.getRole(entry.getCompanyId(), "Site Member");
	        	System.out.println(" Role Name : " + role.getName() + " Role ID : " + role.getRoleId());
	        	
	        	
	        	//setting view permission to uploading images for sitemember role.
	        	long resource = ResourceLocalServiceUtil.getResource(entry.getCompanyId(),DLFileEntry.class.getName(),ResourceConstants.SCOPE_INDIVIDUAL,String.valueOf(entry.getFileEntryId())).getResourceId();
        		String[] actionsRW = new String[]{ActionKeys.VIEW, ActionKeys.UPDATE};
        		long roleId = role.getRoleId(); /* obtain a roleId here */
        		ResourcePermission resourcePermission = null;
        		             try
        		            {
        		                resourcePermission = ResourcePermissionLocalServiceUtil.getResourcePermission(entry.getCompanyId(),
        		                        DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(entry
        		                                 .getPrimaryKey()), roleId);
        		 
        		               
        		                 if (Validator.isNotNull(resourcePermission))
        		                {
        		                 
        		                    //resourcePermission.setActionIds(actionIds);
        		                    ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);
        		                }
        		            } catch (Exception e)
        		            {
        		
        		                resourcePermission = ResourcePermissionLocalServiceUtil
        		                        .createResourcePermission(CounterLocalServiceUtil.increment());
        		               resourcePermission.setCompanyId(entry.getCompanyId());
        		                resourcePermission.setName(DLFileEntry.class.getName());
        		                resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
        		                resourcePermission.setPrimKey(String.valueOf(entry.getPrimaryKey()));
        		                resourcePermission.setRoleId(roleId);
        		                resourcePermission.setActionIds(1);// (ActionKeys.VIEW);
        		                ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);
        		            }
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        }
	       // FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,folderId,file.getName(), null, file.getName(), null, null, fis,file.getTotalSpace(), new ServiceContext());
	}

}