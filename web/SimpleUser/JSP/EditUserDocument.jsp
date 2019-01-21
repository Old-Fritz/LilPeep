<%@ page import="DataBaseAcces.Entities.UserDocumentField" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="document" type="DataBaseAcces.Entities.UserDocument" scope="request"></jsp:useBean>

<script>
    function deleteDocument() {
        let xhr = new XMLHttpRequest();
        let queryString = "documentID=" + <jsp:getProperty name="document" property="id"/>;

        xhr.open("DELETE","editDocument?"+queryString, true);
        xhr.send(null);
        window.location = "documents";
    }
</script>

<h><%=document.getDocumentKind().getName()%></h>
<br>
<%=document.getDocumentKind().getDescription()%>
<br>

<form method="POST" action="<%=request.getContextPath()%>/user/editDocument">
    <input type="hidden" name="documentID" value="<jsp:getProperty name="document" property="id"/>">
    <%
        List<UserDocumentField> fields = document.getUserDocumentFields();
        for(int i = 0;i<fields.size();i++){%>
            <%=fields.get(i).getField().getName()%><br>
            <input type="text" name="field<%=i%>" value="<%=fields.get(i).getValue()%>"/><br>
    <%  }%>
    <button type="submit">Change</button>
    <button onclick="window.history.back()">Back</button>
    <button onclick="deleteDocument()">Delete</button>
</form>


<br><br><br>
<button onclick="window.location='addDocument'">Add document</button>
<button onclick="window.location='documents'">Documents</button>
<button onclick="window.location='settings'">Settings</button>
<button onclick="window.location='../logout'">Logout</button>