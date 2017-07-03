package employee.directory.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author tushar.patel	
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.configuration-action-class=employee.directory.portlet.EmployeeDirectoryConfiguration",
		"javax.portlet.display-name=Employee-Directory Portlet",
		"javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.header-portlet-javascript=/js/jquery.dataTables.min.js",
		"com.liferay.portlet.header-portlet-css=/css/employeedirectory.css",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/config.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeeDirectoryPortlet extends MVCPortlet {
}