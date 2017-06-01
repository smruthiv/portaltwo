package SSI.Profile.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.model.Address;
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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
		String password = null;
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = themeDisplay.getUser();
			String firstName = ParamUtil.get(actionRequest, "firstName", user.getFirstName());
			String lastName = ParamUtil.get(actionRequest, "lastName", user.getLastName());
			String skype = ParamUtil.get(actionRequest, "skype",  user.getContact().getSkypeSn());
			String phoneNumber = ParamUtil.get(actionRequest, "phoneNumber", "" );
			String password1 = ParamUtil.get(actionRequest, "password1", "" );
			
			//String password1 = ParamUtil.get(actionRequest, "password1", "kl");
			
			Contact contact = user.getContact();
			contact.setSkypeSn(skype);
			ContactLocalServiceUtil.updateContact(contact);
			
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest); 
		       ByteArrayFileInputStream inputStream = null;
			File file = uploadPortletRequest.getFile("userPic");
            if (!file.exists()) {
              System.out.println("Empty File");
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
			String street1 = ParamUtil.get(actionRequest, "street1", user.getAddresses().get(0).getStreet1());
			String street2 = ParamUtil.get(actionRequest, "street2", user.getAddresses().get(0).getStreet2());
			String street3 = ParamUtil.get(actionRequest, "street3", user.getAddresses().get(0).getStreet3());
			String city = ParamUtil.get(actionRequest, "city", user.getAddresses().get(0).getCity());
			long country =(long)ParamUtil.get(actionRequest, "country", user.getAddresses().get(0).getCountry().getCountryId());
			//String region = ParamUtil.get(actionRequest, "region", user.getAddresses().get(0).getRegion());
			Address  address = user.getAddresses().get(0);
			address.setStreet1(street1);
			address.setStreet2(street2);
			address.setStreet3(street3);
			address.setCity(city);
			if(country!=0){
				address.setCountryId(country);
			}
			AddressLocalServiceUtil.updateAddress(address);
		}
		else{
				String street1 = ParamUtil.get(actionRequest, "street1","");
				String street2 = ParamUtil.get(actionRequest, "street2", "");
				String street3 = ParamUtil.get(actionRequest, "street3", "");
				String city = ParamUtil.get(actionRequest, "city", "");
				long countryId =(long)ParamUtil.get(actionRequest, "country", 0);
				/*Address address = AddressLocalServiceUtil.createAddress(0);
				address.setStreet1(street1);
				address.setStreet2(street2);
				address.setStreet3(street3);
				address.setCity(city);
				if(countryId!=0){
					address.setCountryId(countryId);
				}*/
				AddressLocalServiceUtil.addAddress(user.getUserId(), Address.class.getName(), PortalUtil.getClassNameId(Address.class.getName()), street1, street2, street3, city, "", 0, countryId, 11000, false, true, new ServiceContext());
		}
		
			if(user.getPhones()!=null&&user.getPhones().size()>0&&user.getPhones().get(0)!=null){
				System.out.println("UPDATE PHONES");
				Phone phone = user.getPhones().get(0);
				phone.setNumber(phoneNumber);
				phone.setTypeId(11011);
				phone.setUserId(user.getUserId());
				PhoneLocalServiceUtil.updatePhone(phone);
			}
			else{
				Phone newphone = PhoneLocalServiceUtil.createPhone(CounterLocalServiceUtil
						.increment(Phone.class.getName()));
				PhoneLocalServiceUtil.addPhone(user.getUserId(),
                        Contact.class.getName(),
                        user.getContactId(),
                        phoneNumber,
                        "",
                        11011,
                        true, new ServiceContext());
				 
			}
			user.setFirstName(firstName);
			user.setLastName(lastName);
			UserLocalServiceUtil.updateUser(user);
			
			if(password1!=null && !password1.isEmpty()){
				UserLocalServiceUtil.updatePassword(user.getUserId(), password1, password1, false); 
			}	
			if(data!=null){
				UserServiceUtil.updatePortrait(user.getUserId(), data);
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		super.processAction(actionRequest, actionResponse);
	}
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.render(renderRequest, renderResponse);
	}
}