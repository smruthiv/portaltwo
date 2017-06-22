package image.gallery.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
	private static Log logger = LogFactoryUtil.getLog(ImageGalleryPortlet.class);
	private static final String SITE_MEMEBER =  "Site Member";
	private static final String IMAGE_GALLERY =  PropsUtil.get("image.gallery");
	
	
	
	public static void createDocument(ActionRequest req,ActionResponse res){
		try{
		logger.info("Image Gallery props :-"+IMAGE_GALLERY);
		ThemeDisplay themedisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themedisplay.getScopeGroupId();
		long userId = themedisplay.getUserId();
		List<DLFolder> folderList = DLFolderLocalServiceUtil.getFolders(groupId,0);
		String eventFolderName = ParamUtil.getString(req, "folderName");
		String eventFolderDesc = ParamUtil.getString(req, "folderDesc");
		for(DLFolder folder : folderList){
			if(IMAGE_GALLERY.equalsIgnoreCase(folder.getName())){
			logger.info("Found folder :-"+IMAGE_GALLERY);
			long folderId = folder.getFolderId();
			Folder dlforler =  DLAppLocalServiceUtil.addFolder(userId, themedisplay.getScopeGroupId(), folderId, eventFolderName, eventFolderDesc, new ServiceContext());
        	Role role = RoleLocalServiceUtil.getRole(dlforler.getCompanyId(), SITE_MEMEBER);
        	ResourcePermissionLocalServiceUtil.setResourcePermissions(themedisplay.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlforler.getFolderId()), role.getRoleId(), new String[]{"VIEW"});
			res.setRenderParameter("newFolderId", dlforler.getFolderId()+"");
			res.setRenderParameter("newFolderName", eventFolderName+"");
			}
			res.setRenderParameter("mvcPath", "/html/jsps/addImages.jsp");
		}
		}
		catch(Exception exception){
			logger.error(exception);
		}
	}
	
	public static void uploadImages(ActionRequest req,ActionResponse res) {
		try{
		ThemeDisplay themedisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		 UploadPortletRequest uploadrequest = PortalUtil.getUploadPortletRequest(req);
		 final FileItem[] arr = uploadrequest.getMultipartParameterMap().get("upload_images");
	        final long repoId = themedisplay.getScopeGroupId();
	        final long folderId = ParamUtil.getLong(uploadrequest, "newlyCreatedFolder");
	        for (final FileItem file : arr) {
	        	InputStream fis = file.getInputStream();
	        	FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,folderId,file.getFileName(), null, file.getFileName(), null, null, fis,file.getStoreLocation().getTotalSpace(), new ServiceContext());
	        	Role role = RoleLocalServiceUtil.getRole(entry.getCompanyId(), SITE_MEMEBER);
        		long roleId = role.getRoleId();
        		ResourcePermission resourcePermission = null;
        		             try
        		            {
        		                resourcePermission = ResourcePermissionLocalServiceUtil.getResourcePermission(entry.getCompanyId(),
        		                        DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(entry
        		                                 .getPrimaryKey()), roleId);
        		                 if (Validator.isNotNull(resourcePermission))
        		                {
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
        		                resourcePermission.setActionIds(1);
        		                ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);
        		                logger.error(e);
        		            }
        		
        }
		}
		catch(Exception e){
			logger.error(e);			
		}
	}

	
	public static void uploadImagesOldFolder(ActionRequest req,ActionResponse res) throws PortalException, SystemException, IOException{
		ThemeDisplay themedisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
		 UploadPortletRequest uploadrequest = PortalUtil.getUploadPortletRequest(req);
		 final FileItem[] arr = uploadrequest.getMultipartParameterMap().get("upload_images");
	        final long repoId = themedisplay.getScopeGroupId();
	        final long folderId = ParamUtil.getLong(uploadrequest, "eventFolder");
	        for (final FileItem file : arr) {
	        	InputStream fis = file.getInputStream();
	        	FileEntry entry = DLAppServiceUtil.addFileEntry(repoId,folderId,file.getFileName(), null, file.getFileName(), null, null, fis,file.getStoreLocation().getTotalSpace(), new ServiceContext());
	        	Role role = RoleLocalServiceUtil.getRole(entry.getCompanyId(), SITE_MEMEBER);
        		long roleId = role.getRoleId();
        		ResourcePermission resourcePermission = null;
        		             try
        		            {
        		                resourcePermission = ResourcePermissionLocalServiceUtil.getResourcePermission(entry.getCompanyId(),
        		                        DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(entry
        		                                 .getPrimaryKey()), roleId);
        		 
        		                 if (Validator.isNotNull(resourcePermission))
        		                {
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
        		                resourcePermission.setActionIds(1);
        		                ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);
        		                logger.error(e);
        		            }
	        }
	}

}