package com.ssi.holiday.controller.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ssi.holiday.service.model.Holiday;
import com.ssi.holiday.service.service.HolidayLocalServiceUtil;

/**
 * @author Mohammed.Rahamatulla
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Holiday",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class HolidayControllerPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
			List<Holiday> holidayList = HolidayLocalServiceUtil.getCurrentYearHolidays();
			renderRequest.setAttribute("holidayList", holidayList);
			super.doView(renderRequest, renderResponse);
	}
}