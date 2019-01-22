<%@ page import="DataBaseAcces.Entities.UserDocument" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<UserDocument> documents = (List<UserDocument>)request.getAttribute("documents");
for(UserDocument document:documents){%>
    <%=document.getDocumentKind().getName()%>
    <button onclick="window.location = 'editDocument?documentID=<%=document.getId()%>'">open</button>
    <br>
<%}%>