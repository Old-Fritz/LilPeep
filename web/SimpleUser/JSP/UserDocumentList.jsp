<%@ page import="Entities.UserDocument" %>
<%@ page import="java.util.List" %>



<%List<UserDocument> documents = (List<UserDocument>)request.getAttribute("documents");
    for(UserDocument document:documents){%>
        <%=document.getDocumentKind().getName()%>
        <br>
    <%}%>