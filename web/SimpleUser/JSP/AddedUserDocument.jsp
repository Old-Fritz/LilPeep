<%@ page import="Entities.Field" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="document" type="Entities.DocumentKind" scope="request"></jsp:useBean>

<h><%=document.getName()%></h>
<br>
<%=document.getDescription()%>
<br>
<br>

<form method="POST" action="<%=request.getContextPath()%>/user/addedDocument">
    <input type="hidden" name="documentID" value="<jsp:getProperty name="document" property="id"/>">
    <%
        List<Field> fields = document.getFields();
        for(int i = 0;i<document.getFieldsCount();i++){%>
            <%=fields.get(i).getName()%><br>
            <input type="text" name="field<%=i%>"/><br>
    <%  }%>
    <button type="submit">Create</button>
    <button onclick="window.history.back()">Back</button>
</form>


<br><br><br>
<button onclick="window.location='addDocument'">Add document</button>
<button onclick="window.location='documents'">Documents</button>
<button onclick="window.location='settings'">Settings</button>
<button onclick="window.location='../logout'">Logout</button>