package SSI.Profile.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.ContactLocalServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author tushar.patel
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=SSI-Profile Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SSIProfilePortlet extends MVCPortlet {
	
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
			byte[] data = null;
			boolean	updatePassword = true;
			updatePassword = validatePassword(actionRequest, actionResponse);
			if(updatePassword){
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long userId = themeDisplay.getUser().getUserId();
			User user = UserLocalServiceUtil.getUser(userId);
			String firstName = ParamUtil.get(actionRequest, "firstName", "");
			String lastName = ParamUtil.get(actionRequest, "lastName", "");
			_log.info("Last Name"+lastName);
			String skype = ParamUtil.get(actionRequest, "skype",  "");
			String phoneNumber = ParamUtil.get(actionRequest, "phoneNumber", "" );
			Contact contact = user.getContact();
			contact.setSkypeSn(skype);
			ContactLocalServiceUtil.updateContact(contact);
			
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest); 
		    ByteArrayFileInputStream inputStream = null;
			File file = uploadPortletRequest.getFile("userPic");
            if (!file.exists()) {
            _log.info("Empty File");
           }
          if ((file != null) && file.exists()) {
                 inputStream = new ByteArrayFileInputStream(file, 1024);
                  
                  try {
                         data = FileUtil.getBytes(inputStream);
                    } catch (IOException e) {
                            e.printStackTrace();
                     }
                  
          }
		
		if(user.getAddresses()!=null&&user.getAddresses().size()>0&&user.getAddresses().get(0)!=null){
			_log.info("Update Address");
			String street1 = ParamUtil.get(actionRequest, "street1", "");
			String street2 = ParamUtil.get(actionRequest, "street2", "");
			String street3 = ParamUtil.get(actionRequest, "street3", "");
			String city = ParamUtil.get(actionRequest, "city", "");
			long country =(long)ParamUtil.get(actionRequest, "country", 0);
			String zipcode = ParamUtil.get(actionRequest, "zipcode","");
			Address  address = user.getAddresses().get(0);
			address.setStreet1(street1);
			address.setStreet2(street2);
			address.setStreet3(street3);
			address.setCity(city);
			address.setZip(zipcode);
			if(country!=0){
				address.setCountryId(country);
			}
			AddressLocalServiceUtil.updateAddress(address);
		}
		else{
				_log.info("Add Address");
				String street1 = ParamUtil.get(actionRequest, "street1","");
				String street2 = ParamUtil.get(actionRequest, "street2", "");
				String street3 = ParamUtil.get(actionRequest, "street3", "");
				String city = ParamUtil.get(actionRequest, "city", "");
				String zipcode = ParamUtil.get(actionRequest, "zipcode", "");
				long countryId =(long)ParamUtil.get(actionRequest, "country", 0);
				_log.info("Zip Code"+zipcode);
				_log.info("Country Code"+countryId);
				if(!street1.isEmpty()&&!city.isEmpty()){
					AddressLocalServiceUtil.addAddress(user.getUserId(), Contact.class.getName(), user.getContactId(), street1, street2, street3, city, zipcode, 0, countryId, 11000, false, false, new ServiceContext());
				}
		}
		
			if(user.getPhones()!=null&&user.getPhones().size()>0&&user.getPhones().get(0)!=null){
				//_log.info("UPDATE PHONES");
				Phone phone = user.getPhones().get(0);
				phone.setNumber(phoneNumber);
				phone.setTypeId(11011);
				phone.setUserId(user.getUserId());
				PhoneLocalServiceUtil.updatePhone(phone);
			}
			else{
				if(phoneNumber!=null && !phoneNumber.isEmpty()){
					PhoneLocalServiceUtil.addPhone(user.getUserId(),Contact.class.getName(),user.getContactId(),phoneNumber,"",11011,true, new ServiceContext());
				}
			}
			User userInfo = UserLocalServiceUtil.getUser(userId);
			userInfo.setFirstName(firstName);
			userInfo.setLastName(lastName);
			UserLocalServiceUtil.updateUser(userInfo);
			
			if(data!=null){
				UserServiceUtil.updatePortrait(user.getUserId(), data);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		_log.info("Profile saved succesfully");
		updatePassword(actionRequest,actionResponse);
		SessionMessages.add(actionRequest, "success");
	}
			
			
		super.processAction(actionRequest, actionResponse);
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User objUser = themeDisplay.getUser();
		_log.info("Render Method called");
		super.render(renderRequest, renderResponse);
	}
	
	
	public boolean validatePassword(ActionRequest actionRequest, ActionResponse actionResponse){
	ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	_log.info(":::::::::::::calling validate Password::::::::::::::::");
	String current = ParamUtil.getString(actionRequest, "current","");
	String password1 = ParamUtil.getString(actionRequest, "password1","");
	String password2 = ParamUtil.getString(actionRequest, "password2","");
	String fname = ParamUtil.getString(actionRequest, "fname","");
	_log.info(":::::::::::::Current Password::::::::::::::::" + current+"======");
	_log.info(":::::::::::::Password 1::::::::::::::::" + password1+"======");
	_log.info(":::::::::::::Password 2::::::::::::::::" + password2+"======");
	_log.info(":::::::::::::fname::::::::::::::::" + fname+"======");


	String street1 = ParamUtil.get(actionRequest, "street1", "");
	String city = ParamUtil.get(actionRequest, "city", "");
	boolean isErrorOccured=false;
	_log.info(":::::::::::::Street 1::::::::::::::::" + street1+"======");
	String orgPassword = null;
	if(fname !=null && !fname.isEmpty() && fname.length()>2){
		orgPassword = fname.substring(1);
		orgPassword = orgPassword.substring(0,orgPassword.length()-1);
		if(orgPassword.startsWith(" ")||orgPassword.endsWith(" ")){
			SessionErrors.add(actionRequest, "password-startwith-space");
			isErrorOccured = true;
		}
		_log.info(":::::::::::::orgPasseord::::::::::::::::" + orgPassword+"======");
	}
	
	if(street1.isEmpty()&&city.isEmpty()){
		
	}
	else if(street1.isEmpty()&&!city.isEmpty()){
		SessionErrors.add(actionRequest, "street1-required");
		isErrorOccured = true;
	}
	else if(!street1.isEmpty()&&city.isEmpty()){
		SessionErrors.add(actionRequest, "city-required");
		isErrorOccured = true;
	}
	if(current!=null&&current.isEmpty()&&password1!=null&&password1.isEmpty()&&password2!=null&&password2.isEmpty()){
		_log.info("All Password is empty");
		
	}
	else if(password1!=null&&password1.isEmpty()&&password2!=null&&password2.isEmpty()){
		_log.info("Password 1 and 2 is empty");
		
	}
	else{
	if(current!=null&&current.isEmpty()&&!password1.isEmpty()&&!password2.isEmpty()){
		SessionErrors.add(actionRequest, "name-is-required");
		isErrorOccured = true;
	}
	if(!password1.equals(password2))
	{
		SessionErrors.add(actionRequest, "confirm-new-password");
		isErrorOccured = true;
	}
	
	try {
	String authType = themeDisplay.getCompany().getAuthType();
	String login = "";
	/**
	* authType can be of three types.
	* Therefore based on authType login can email address or
	* screen name or user id of the logged in user
	*/
	if(authType.equals(CompanyConstants.AUTH_TYPE_EA)){
	login = themeDisplay.getUser().getEmailAddress();
	}else if(authType.equals(CompanyConstants.AUTH_TYPE_SN)){
	login = themeDisplay.getUser().getScreenName();
	}else if(authType.equals(CompanyConstants.AUTH_TYPE_ID)){
	login = String.valueOf(themeDisplay.getUser().getUserId());
	}

	/**
	* The method authenticateForBasic returns userId of the logged in user if all
	* the parameters in the method are correct. Otherwise it will return 0.
	* Notice the if condition
	*/
		if(current!=null && !current.isEmpty()){
			long userId=UserLocalServiceUtil.authenticateForBasic(themeDisplay.getCompanyId(), authType, login, current);
			try{
			if(themeDisplay.getUserId()!=userId)
			{
				SessionErrors.add(actionRequest, "invalid-current-password");
				isErrorOccured = true;
			}
			}
			 catch (Exception e) {
				 	SessionErrors.add(actionRequest, "invalid-current-password");
					isErrorOccured = true;
					_log.error(e.getMessage(), e);
			}
		}	
		if(orgPassword!=null && !orgPassword.isEmpty()){
			long userId	= UserLocalServiceUtil.authenticateForBasic(themeDisplay.getCompanyId(), authType, login, orgPassword);
			if(userId!=0)
			{
				SessionErrors.add(actionRequest, "new-password-cant-be-same-as-old-password");
				isErrorOccured = true;
			}
		}	
	} catch (PortalException e) {
	_log.error(e.getMessage(), e);
	} catch (SystemException e) {
	_log.error(e.getMessage(), e);
	} catch (Exception e) {
	_log.error(e.getMessage(), e);
	}
	}
	if(isErrorOccured){
		return false;
	}
	return true;
}
	
	public boolean updatePassword(ActionRequest actionRequest, ActionResponse actionResponse){
	ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	_log.info(":::::::::::::calling updatePassword::::::::::::::::");
	String current = ParamUtil.getString(actionRequest, "current","");
	String password1 = ParamUtil.getString(actionRequest, "password1","");
	String password2 = ParamUtil.getString(actionRequest, "password2","");
	if(current!=null&&current.isEmpty()&&password1!=null&&password1.isEmpty()&&password2!=null&&password2.isEmpty()){
		_log.info("All Password is empty");
		return true;
	}
	if(password1!=null&&password1.isEmpty()&&password2!=null&&password2.isEmpty()){
		_log.info("Password 1 and 2 is empty");
		return true;
	}
	
	if(current!=null&&current.isEmpty()&&!password1.isEmpty()&&!password2.isEmpty()){
		return false;
	}
	if(!password1.equals(password2))
	{
		return false;
	}
	
	String errorKey = "";

	try {
	String authType = themeDisplay.getCompany().getAuthType();
	String login = "";
	/**
	* authType can be of three types.
	* Therefore based on authType login can email address or
	* screen name or user id of the logged in user
	*/
	if(authType.equals(CompanyConstants.AUTH_TYPE_EA)){
	login = themeDisplay.getUser().getEmailAddress();
	}else if(authType.equals(CompanyConstants.AUTH_TYPE_SN)){
	login = themeDisplay.getUser().getScreenName();
	}else if(authType.equals(CompanyConstants.AUTH_TYPE_ID)){
	login = String.valueOf(themeDisplay.getUser().getUserId());
	}

	/**
	* The method authenticateForBasic returns userId of the logged in user if all
	* the parameters in the method are correct. Otherwise it will return 0.
	* Notice the if condition
	*/

	long userId=UserLocalServiceUtil.authenticateForBasic(themeDisplay.getCompanyId(), authType, login, current);
	if(themeDisplay.getUserId()!=userId)
	{
		return false;
	}
	UserLocalServiceUtil.updatePassword(userId, password1, password2, false);

	} catch (PortalException e) {
	_log.error(e.getMessage(), e);
	} catch (SystemException e) {
	_log.error(e.getMessage(), e);
	} catch (Exception e) {
	_log.error(e.getMessage(), e);
	}

	if(Validator.isNull(errorKey)){
	SessionMessages.add(actionRequest, "password-update-success");
	}else{
	SessionErrors.add(actionRequest, errorKey);
	}
	return true;
	}
	
	private Log _log = LogFactoryUtil.getLog(SSIProfilePortlet.class.getName());
}




