<%@ page import="java.util.List" %>
<%@ page import="Entities.DocumentKind" %>

<%List<DocumentKind> documents = (List<DocumentKind>)request.getAttribute("documents");
for(DocumentKind document:documents){%>
    <%=document.getName()%>
    <button onclick="window.location = 'addedDocument?documentID=<%=document.getId()%>'">create</button>
    <br>
<%}%>