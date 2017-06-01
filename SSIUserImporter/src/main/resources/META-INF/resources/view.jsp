<%@ include file="/init.jsp" %>

<portlet:actionURL name="addStack" var="actionFoo"></portlet:actionURL>
<form action="${actionFoo.toString()}" method="post" enctype="multipart/form-data">
 <div class="form-group">
 <input type="file" name="file"/>
 </div>
 <input class="btn btn-info" type="submit" value="Submit" />
</form>