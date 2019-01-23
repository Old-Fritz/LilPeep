<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBaseAcces.Entities.DocumentKind" %>


<c:forEach var="document" items="documents">
    showDocument();
</c:forEach>





<script>
    function addDocument(id) {
        let xhr = new XMLHttpRequest();

        let body = "documentID="+encodeURIComponent(id) +
        "&formID=" + encodeURIComponent(<%=request.getParameter("formID")%>)+
        "&type="+encodeURIComponent("document");

        xhr.open("POST","owner/editForm", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            window.location="editForm?formID="+ <%=request.getParameter("formID")%>;
        };
        xhr.send(body);
    }
</script>
<%  List<DocumentKind> documents = (List<DocumentKind>)request.getAttribute("documents");
    for(DocumentKind document:documents){%>
        <%=document.getName()%>
        <button onclick="addDocument(<%=document.getId()%>)">add</button>
        <br>
<%  }%>