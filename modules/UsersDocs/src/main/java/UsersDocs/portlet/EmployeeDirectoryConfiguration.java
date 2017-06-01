package UsersDocs.portlet;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(
	    configurationPid = "UsersDocs.portlet.EmployeeDirectoryConfiguration",
		    configurationPolicy = ConfigurationPolicy.OPTIONAL,
		    immediate = true,
		    property = {
		        "javax.portlet.name=UsersDocs_portlet_UsersDocsPortlet"
		    },
		    service = ConfigurationAction.class
		)
public class EmployeeDirectoryConfiguration extends DefaultConfigurationAction{
	@Override
	public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		System.out.println("Render Called");
		super.render(request, response);
	}
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
		System.out.println("HELLO");
		String userGroupId =ParamUtil.getString(actionRequest,"userGroup");
		PortletPreferences portletPreferences = actionRequest.getPreferences();
		portletPreferences.setValue("userGroupId", userGroupId);
		portletPreferences.store();
		super.processAction(portletConfig, actionRequest, actionResponse);
			
	}
	

	
	/*@Override
	public String render(PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws Exception {
		// TODO Auto-generated method stub
		PortletPreferences portletPreferences = renderRequest.getPreferences();
		renderRequest.setAttribute("userGroupId",portletPreferences.getValue("userGroupId", "0") );
		System.out.println("REQUESST"+renderRequest.getAttribute("userGroupId"));
		return super.render(portletConfig, renderRequest, renderResponse);
	}*/
}
