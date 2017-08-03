<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
	<head>
		<title>${the_title} - ${company_name}</title>

		<meta content="initial-scale=1.0, width=device-width" name="viewport" />
		<@liferay.js file_name="${javascript_folder}/jquery.min.js"/> 
		<@liferay_util["include"] page=top_head_include />
		<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700" rel="stylesheet">

	</head>
  
	<body class="${css_class}">
		<@liferay_ui["quick-access"] contentId="#main-content" />

		<@liferay_util["include"] page=body_top_include />

		<@liferay.control_menu />

		<div id="wrapper">
			
			<script type="text/javascript">
//<![CDATA[
var cp = window.location.href.indexOf("control_panel");
if(cp < 0){
var cssId = 'myCss';
if (!document.getElementById(cssId))
{
    var head  = document.getElementsByTagName('head')[0];
    var link  = document.createElement('link');
    link.id   = cssId;
    link.rel  = 'stylesheet';
    link.type = 'text/css';
    link.href = '/o/SSI-theme/css/aui.css';
    link.media = 'all';
    head.appendChild(link);
    var headaui  = document.getElementsByTagName('head')[0];
    var linkaui  = document.createElement('link');
    linkaui.rel  = 'stylesheet';
    linkaui.type = 'text/css';
    linkaui.href = '/o/SSI-theme/css/main.css';
    linkaui.media = 'all';
    headaui.appendChild(linkaui);
}
}
//]]>
</script>
			<header class="container-fluid-1280" id="banner" role="banner">
			
				<div class="navbar-header" id="heading">
					<#if has_navigation>
						<button aria-controls="navigation" aria-expanded="false" class="collapsed navbar-toggle" data-target="#navigationCollapse" data-toggle="collapse" type="button">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</#if>

					<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						<img alt="${logo_description}" height="56" src="${site_logo}" />
					</a>
				</div>

				<#if has_navigation>
					<#include "${full_templates_path}/navigation.ftl" />
				</#if>
			</header>
<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone")>

        <nav id="breadcrumbs">
            <@liferay.breadcrumbs default_preferences="${freeMarkerPortletPreferences}"  />
        </nav>

      <#assign VOID = freeMarkerPortletPreferences.reset()>
			<main id="content" role="main">
				<h1 class="hide-accessible">${the_title}</h1>

				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}

					${portletDisplay.setTitle(the_title)}

					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>
				
			</main>
			
			
      <#include "${full_templates_path}/footer.ftl" />
		</div>

		<@liferay_util["include"] page=body_bottom_include />

		<@liferay_util["include"] page=bottom_include />
		
		<div class="policy"><div id="warning"><img src="${images_folder}/icons/warning.png"> <b>WARNING</b></div>

This is a Social Solutions International computer system. This system is provided for the processing of Social Solutions International business only. All data contained on this computer systems is owned by Social Solutions International and may be monitored, intercepted, recorded, read, copied, or captured in any manner and disclosed in any manner, by authorized personnel. THERE IS NO RIGHT OF PRIVACY IN THIS SYSTEM. System personnel may give to law enforcement officials any potential evidence of crime found on this computer systems. USE OF THIS SYSTEM BY ANY USER, AUTHORIZED OR UNAUTHORIZED, CONSTITUTES CONSENT TO THIS MONITORING, INTERCEPTION, RECORDING, READING, COPYING, OR CAPTURING and DISCLOSURE. Unauthorized access or use of this computer system may subject violators to criminal, civil, and/or administrative action.
</div>


<script>
$(document).ready(function(){
	 
	$(checkForPanel);
function checkForPanel()
{
    if ($('#_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavSliderId').hasClass('open')){
		
       $('#_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavSliderId a').attr('target','_blank');}
    
}
$(".product-menu-toggle").click(
    function(){
        console.log("opening side panel");
        setTimeout(
            function() {$('#_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavSliderId a').attr('target','_blank');},2000);
    });
});


</script>


	</body>
</html>