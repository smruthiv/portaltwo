package employee.directory.portlet;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(
	    configurationPid = "employee.directory.portlet.EmployeeDirectoryConfiguration",
		    configurationPolicy = ConfigurationPolicy.OPTIONAL,
		    immediate = true,
		    property = {
		        "javax.portlet.name=employee_directory_portlet_EmployeeDirectoryPortlet"
		    },
		    service = ConfigurationAction.class
		)
public class EmployeeDirectoryConfiguration extends DefaultConfigurationAction{
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
		String userGroupId =ParamUtil.getString(actionRequest,"userGroup");
		PortletPreferences portletPreferences = actionRequest.getPreferences();
		portletPreferences.setValue("userGroupId", userGroupId);
		portletPreferences.store();
		super.processAction(portletConfig, actionRequest, actionResponse);
			
	}
}
