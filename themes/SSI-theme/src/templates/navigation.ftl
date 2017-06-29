<div aria-expanded="true" class="collapse navbar-collapse" id="navigationCollapse">
<div id="rightMenu">





			<div class="userAccount">
			<ul class="userMenu">
			<li><a href="#" onclick="toggleUserMenu();return false;">
			<span class="user-avatar-image">
					
													<img class =" aspect-ratio-bg-cover user-icon" src ="${user_avatar_url}" alt="user avatar" title="${user_name}"/>
												
				</span>
				<span class="user-full-name">
					${user_name}
				</span>
								
								</a>
			<ul class="userDropdown">
			<li><a href="/group/ssi/profile"><img src="${images_folder}/icons/drop_user_profile.svg" />My Profile</a><li>
			
			
			
			
			
			<#if is_signed_in && permissionChecker.isOmniadmin()>

<li><a href="#" onclick="toggleDockbar();return false;" title="Settings"><img src="${images_folder}/icons/drop_settings.svg" />Settings</a></li>
</#if>

		
			<#if is_signed_in>
			<li><a href="${sign_out_url}" id="sign-out" rel="nofollow" title="Logout"><img src="${images_folder}/icons/drop_logout.svg" />Logout</a></li>
			</#if>	
			
			</ul>
			</li>
			</ul>
							
			</div>
	<#if main_search_class != "no-screen">
		<nav id="search" role="navigation">
			<div class="${main_search_class} navbar-form navbar-right" role="search">
				<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />

				<@liferay.search default_preferences="${freeMarkerPortletPreferences}" />

				<#assign VOID = freeMarkerPortletPreferences.reset() />
			</div>
		</nav>
	</#if>
</div>
	<nav class="nav-header-global row" role="navigation" >
	
			<#assign
				VOID = freeMarkerPortletPreferences.setValue("displayDepth", "3")
				VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone")
			/>

			

			<#assign VOID = freeMarkerPortletPreferences.reset() />
<#assign groupService1 = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")>
<#assign parentSiteName = "Guest">
<#assign parentSiteGroup = groupService1.getGroup(theme_display.getCompanyId(),parentSiteName)>
<#assign layoutService = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")>
<#assign parentLayoutPlid = layoutService.getDefaultPlid(parentSiteGroup.getGroupId(), true)>
<#assign parentLayouts = layoutService.getLayouts(parentSiteGroup.getGroupId(), true,0)>
<#assign groupService1 = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")>
<#assign navItemClass = staticUtil["com.liferay.portal.kernel.theme.NavItem"] /> 
<#assign parentNavItems = navItemClass.fromLayouts(request,parentLayouts, null)>
<#assign groupService1 = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")>
<#assign layoutUtil = serviceLocator.findService("com.liferay.portal.kernel.service.permission.LayoutPermissionUtil")>
	<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>

	<ul aria-label="<@liferay.language key="site-pages" />" role="menubar" class="nav navbar-nav">
		<#list parentNavItems as nav_item>
		
		<#if theme_display.getPermissionChecker().hasPermission(nav_item.getLayout().getGroupId(),"com.liferay.portal.kernel.model.Layout", nav_item.getLayout().getPrimaryKey(),"VIEW") >		
		<#if nav_item.getLayout().isHidden()==false>

				<#assign
				nav_item_attr_has_popup = ""
				nav_item_attr_selected = ""
				nav_item_chevron=""
				nav_item_css_class = ""
				nav_item_layout = nav_item.getLayout()
			/>

			<#if nav_item.isSelected()>
			
				<#assign
					nav_item_css_class = "selected active"
					nav_item_attr_has_popup="aria-haspopup='true'"
					nav_item_attr_selected = "aria-selected='true'"
				
				/>
			</#if>
			<#if nav_item.hasChildren()>
<#assign 
nav_item_css_class = "${nav_item_css_class} hasChildren "
nav_item_chevron='<span><i class="icon-chevron-down"></i></span>'

				



/>
</#if>
			
			<li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
				<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span><span class="downIcon">${nav_item_chevron}</span></a>
				
					<#if nav_item.hasChildren()>
					<ul class="child-menu" role="menu" aria-expanded="false">
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_attr_selected = ""
								nav_child_css_class = ""
							/>

							<#if nav_child.isSelected()>
								<#assign
									nav_child_attr_selected = "aria-selected='true'"
									nav_child_css_class = "selected"
								/>
							</#if>

							<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
								<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
							</li>
						</#list>
					</ul>
				</#if>
				
			</li>
		</#if>
</#if>
		</#list>
	</ul>
	
	</nav>

	
</div>

<script>
function toggleDockbar(){
$('.control-menu').toggle();
}
function toggleUserMenu(){
$('.userDropdown').toggle();

}
</script>
<script>

$(document).ready(function() {
	
	$('.nav-header-global').find('a').each(function() {
		
		if ($(this).attr('href') === ($(location).attr("href"))) {

			$(this).css("color", "#ffbd43");
			

		}

	}

	);
});
</script>