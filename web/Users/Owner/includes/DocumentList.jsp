<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBaseAcces.Entities.DocumentKind" %>


<c:forEach var="document" items="documents">
    showDocument();
</c:forEach>