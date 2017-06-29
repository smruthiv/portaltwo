package com.liferay.blade.samples.lifecycle.loginpreaction;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.util.SessionClicks;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;


/**
 * @author Tushar Patel
 */
@Component(
	immediate = true, property = {"key=logout.events.pre"},
	service = LifecycleAction.class
)
public class LogoutPreAction implements LifecycleAction {
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {
		HttpServletRequest servletRequest = lifecycleEvent.getRequest();
		SessionClicks.put(servletRequest, "com.liferay.product.navigation.product.menu.web_productMenuState", "closed");
	}
}