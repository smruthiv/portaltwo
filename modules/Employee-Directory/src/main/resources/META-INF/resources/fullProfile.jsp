<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<%
System.out.println("MU");
long id = Long.parseLong(request.getParameter("editId"));
User user1= UserLocalServiceUtil.getUser(id);
String pattern="dd-MM-yyyy";
SimpleDateFormat ft = new SimpleDateFormat();
ft.applyPattern(pattern);

%>



            <div class="panel-heading myclass">
              <h3 class="panel-title"><%=user1.getFirstName().concat(" ").concat(user1.getLastName()) %></h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="<%=user1.getPortraitURL(themeDisplay)%>" class="img-circle img-responsive"> </div>
                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
   <%--                    <tr>
                        <td>Employee Id:</td>
                        <%String employeeId = "";
                        if(user1.getExpandoBridge()!=null&&user1.getExpandoBridge().getAttribute("employeeId")!=null){
                        	employeeId =(String)user1.getExpandoBridge().getAttribute("employeeId");
                        }
                        
                        %>
                        <td><%=employeeId%></td>
                      </tr> --%>
                       <tr>
                        <td>Name:</td>
                        <td><%=user1.getFirstName().concat(" ").concat(user1.getLastName())%></td>
                      </tr>
                      
                      <tr>
                        <td>Email:</td>
                        <td><a href="mailto:<%=user1.getEmailAddress()%>"><%=user1.getEmailAddress()%></a></td>
                      </tr>
                       <tr>
                        <td>Gender:</td>
                        <%if(user1.getFemale()){ %>
                        <td>Female</td>
                        <%}else{ %>
                         <td>Male</td>
                        <%}%>
                      </tr>
                      <tr>
                        <td>Job Title:</td>
                        <td><%=user1.getJobTitle()%></td>
                      </tr>
                      
                      
					
					<%if(user1.getAddresses()!=null&&user1.getAddresses().size()>0&&user1.getAddresses().get(0)!=null){%>
	
	                     <tr> 
	                     <td class="address">Address</td>
	                      <td>
                       <%if(user1.getAddresses().get(0).getStreet1()!=null&&!user1.getAddresses().get(0).getStreet1().isEmpty()){ %>
                      		<%=user1.getAddresses().get(0).getStreet1()%><br/>
                      	<%}%>
                      	 <%if(user1.getAddresses().get(0).getStreet2()!=null&&!user1.getAddresses().get(0).getStreet2().isEmpty()){ %>
                      		<%=user1.getAddresses().get(0).getStreet2()%><br/>
                      	<%}%>
                      	 <%if(user1.getAddresses().get(0).getStreet3()!=null&&!user1.getAddresses().get(0).getStreet3().isEmpty()){ %>
                      		<%=user1.getAddresses().get(0).getStreet3()%><br/>
                      	<%}%>
                      	 <%if(user1.getAddresses().get(0).getCity()!=null&&!user1.getAddresses().get(0).getCity().isEmpty()){ %>
                      		<%=user1.getAddresses().get(0).getCity()%><br/>
                      	<%}%>
                       <%if(user1.getAddresses().get(0).getCountry()!=null&&!user1.getAddresses().get(0).getCountry().getName().isEmpty()){ %>
                      		<%=user1.getAddresses().get(0).getCountry().getName()%><br/>
                      	<%}%>
                      	 <%if(user1.getAddresses().get(0).getZip()!=null&&!user1.getAddresses().get(0).getZip().isEmpty()){ %>
                      		<%=user1.getAddresses().get(0).getZip()%>
                      	<%}%>
                     	</td>
                      </tr>
					  <%}else{%>
					      <tr>
					      <td class="address">Address</td>
					      <td ></td>
                      </tr>
					  <%}%>
                      <tr>
                        <td>Contact Number</td>
                        <%if(user1.getPhones()!=null && user1.getPhones().size()>0 && user1.getPhones().get(0)!=null){%>
						<%String phoneNumber = user1.getPhones().get(0).getNumber();%>
							 <td><%=phoneNumber%></td>
						<%}else{%>
							<td></td>
						<%}%>
                      </tr>
                       
				
                    </tbody>
                  </table>
                  
                  
                  <a href="/group/contacts/employee-directory" class="btn btn-primary">Back</a>
               
                </div>
              </div>
            </div>
                 
