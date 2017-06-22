package com.ssi.portlet.portlet;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.osgi.service.component.annotations.Component;

/**
 * @author Tushar.Patel
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=SSIUserImporter Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserImporterControllerPortlet extends MVCPortlet {
	
	final int NUMBER_OF_COLUMN = 3;
	final String EMP_XLS= "EMP.xls";
	private static Log logger = LogFactoryUtil.getLog(UserImporterControllerPortlet.class);
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		logger.info("UserImporterControllerPortlet Render method called");
		super.render(renderRequest, renderResponse);
	}
	
	public void addStack(ActionRequest arq,ActionResponse ars) throws PortalException
    {
	   ThemeDisplay themeDisplay = (ThemeDisplay) arq.getAttribute(WebKeys.THEME_DISPLAY);	
	   UploadPortletRequest uploadPortletRequest=PortalUtil.getUploadPortletRequest(arq);
       File excelFile=(File) uploadPortletRequest.getFile("file");
       List<MyUser> users = new ArrayList<>();
       if(themeDisplay == null){
    	   logger.info("Theme display object is null");
		}
       else
       {
    	   try {
    		   FileInputStream file = new FileInputStream(excelFile);
    		   HSSFWorkbook workbook = new HSSFWorkbook(file);
    		   HSSFSheet sheet = workbook.getSheetAt(0);
    		   Iterator<Row> rowIterator = sheet.iterator();
    		   
    		   while(rowIterator.hasNext()) {
					Row row = rowIterator.next();
					MyUser myUser = new MyUser();
					Iterator<Cell> cellIterator = row.cellIterator();
					int i = 0;
					while(cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if(i%8==0){
							myUser.setLastNamne(cell.getStringCellValue());
						}
						else if(i%8==1){
							myUser.setFirstName(cell.getStringCellValue());
						}
						else if(i%8==2){
							myUser.setJobTitle(cell.getStringCellValue());
						}
						else if(i%8==3){
							myUser.setEmail(cell.getStringCellValue());
						}
						else if(i%8==4){
							if(cell.getStringCellValue()!=null&&!cell.getStringCellValue().trim().isEmpty()&&"Yes".equalsIgnoreCase(cell.getStringCellValue().trim())){
								myUser.setManager(true);
							}
						}
						else if(i%8==5){
							if(cell.getStringCellValue()!=null&&!cell.getStringCellValue().trim().isEmpty()&&"Yes".equalsIgnoreCase(cell.getStringCellValue().trim())){
								myUser.setGHSI(true);
							}
						}
						else if(i%8==6){
							if(cell.getStringCellValue()!=null&&!cell.getStringCellValue().trim().isEmpty()&&"Yes".equalsIgnoreCase(cell.getStringCellValue().trim())){
								myUser.setExecutive(true);
							}
						}
						else if(i%8==7){
							if(cell.getStringCellValue()!=null&&!cell.getStringCellValue().trim().isEmpty()&&"Yes".equalsIgnoreCase(cell.getStringCellValue().trim())){
								myUser.setHR(true);
							}
						}
						i++;
					}
					if(myUser!=null && !"Work Email".equals(myUser.getEmail())){
						logger.info("Email Setting :-"+myUser.getEmail());
						myUser.setUserId(themeDisplay.getUserId());
						String screenname =  myUser.getEmail().trim().split("@")[0];
						myUser.setScreenName(screenname);
						myUser.setCompanyId(themeDisplay.getCompanyId());
						Locale locale = themeDisplay.getLocale();
						myUser.setLocale(locale);
						String password = PwdGenerator.getPassword(8);
						myUser.setPassWord(password);
						users.add(myUser);
					}
				}
			 	
    		   	workbook.close();
    		   	file.close();
    		   	boolean isComplete;
    		   	List<Role> roles = RoleLocalServiceUtil.getRoles(0, RoleLocalServiceUtil.getRolesCount());
    		   	
    		   long managerRoleId = 0;
    		   long ghsiRoleId = 0;
    		   long executiveRoleId = 0;
    		   long hrRoleId = 0;
    		   
    		   for(Role role : roles){
    			   if("Manager".equals(role.getName())){
    				   managerRoleId = role.getRoleId();
    			   }
    			   if("GHSI".equals(role.getName())){
    				   ghsiRoleId = role.getRoleId();
    			   }
    			   if("Executive".equals(role.getName())){
    				   executiveRoleId = role.getRoleId();
    			   }
    			   if("HR".equals(role.getName())){
    				   hrRoleId = role.getRoleId();
    			   }
    		   }
    		   
    		   	isComplete  = createPortalUsers(users, managerRoleId, ghsiRoleId, executiveRoleId, hrRoleId);
    		   	
    		   	if(isComplete){
    		   		isComplete =	createXlsFileForCreatedUsersDetails(users);
    		   	}
    		   	else{
    		   		logger.info("Error while creating users");
    		   	}
    		   	
    		 	if(isComplete){
    		 		uploadFileInDocumentAndLibrary(themeDisplay, new File(EMP_XLS));
    		 	}
    		 	else{
    		   		logger.info("Error while uploading document in documents and library");
    		   	}
    		 	
			} catch (Exception e) {
				logger.error("Error while creating user and reading files"+e);
			} 
	    	
		}
}

	  private boolean createPortalUsers(List<MyUser> myUsers,long managerRoleId,long ghsiRoleId,long executiveRoleId,long hrRoleId){
		  try{
			  User user1;
			  for (MyUser user : myUsers) {
				user1 = UserLocalServiceUtil.addUser(user.getUserId(), user.getCompanyId(), false, user.getPassWord(),
						user.getPassWord(), false, user.getScreenName(), user.getEmail(), 0L, "", user.getLocale(),
						user.getFirstName(), "", user.getLastNamne(), 0, 0, false, 0, 1, 1970, user.getJobTitle(), null, null,
						null, null, false, new ServiceContext());
				
				if(user.isManager()&&managerRoleId!=0){
					RoleLocalServiceUtil.addUserRole(user1.getUserId(), managerRoleId);
				}
				if(user.isGHSI()&&ghsiRoleId!=0){
					RoleLocalServiceUtil.addUserRole(user1.getUserId(), ghsiRoleId);
				}
				if(user.isExecutive()&&executiveRoleId!=0){
					RoleLocalServiceUtil.addUserRole(user1.getUserId(), executiveRoleId);
				}
				if(user.isHR()&&hrRoleId!=0){
					RoleLocalServiceUtil.addUserRole(user1.getUserId(), hrRoleId);
				}
				
				logger.info(
						"New User Created with userId :- " + user1.getUserId() + " password :- " + user.getPassWord());
			  }
		  }
		  catch(Exception e){
			  logger.error("Portal user creation failed " + e);
			  return false;
		  }
		  return true;
	  }
	  
	  boolean createXlsFileForCreatedUsersDetails(List<MyUser> users){
		  	HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Created User");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
		for(int i=0 ; i<users.size();i++){
				data.put(String.valueOf(i+2), new Object[] {users.get(i).getEmail(),users.get(i).getPassWord()});
			}
		
			Set<String> keyset = data.keySet();
			int rownum = 0;
			Row row = sheet.createRow(rownum++);
			Cell cell = row.createCell(0);
			cell.setCellValue((String)"Email");
			cell = row.createCell(1);
			cell.setCellValue((String)"Password");
			
			for (String key : keyset) {
				row = sheet.createRow(rownum++);
				Object [] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					 cell = row.createCell(cellnum++);
					if(obj instanceof Date) 
						cell.setCellValue((Date)obj);
					else if(obj instanceof Boolean)
						cell.setCellValue((Boolean)obj);
					else if(obj instanceof String)
						cell.setCellValue((String)obj);
					else if(obj instanceof Double)
						cell.setCellValue((Double)obj);
				}
			}
			try {
				File empFile1 = new File(EMP_XLS);
				if(empFile1!=null && empFile1.exists()) { 
				    boolean deleted = empFile1.delete();
				    logger.info("File Deleted :- "+deleted);
				    
				}
				File empFile = new File(EMP_XLS);
				FileOutputStream out = new FileOutputStream(empFile);
				workbook.write(out);
				logger.info("Excel written successfully.."+empFile.getAbsolutePath());
				workbook.close();
				out.close();
			} catch (Exception e) {
				logger.error("Error while creating excel file "+e);
				return false;
			}
			return true; 
	  }
	  
	 boolean uploadFileInDocumentAndLibrary(ThemeDisplay themeDisplay,File file){
		 long repositoryId = themeDisplay.getScopeGroupId();
		 try {
			DLAppLocalServiceUtil.addFileEntry(themeDisplay.getUserId(), repositoryId,  DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "SSI Users"+new Date()+".xls", "application/vnd.ms-excel",  "SSI Users "+new Date(),
					 "SSI Users",  "SSI Users", file, new ServiceContext());
		} catch (Exception e) {
			logger.error("Error while uploading excel file in document and library "+e);
			return false;
		}
		 return true;
	  }
	 

}