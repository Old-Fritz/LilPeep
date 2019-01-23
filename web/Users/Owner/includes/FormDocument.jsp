<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="DataBaseAcces.Entities.FormDocumentField" %>
<%@ page import="DataBaseAcces.Entities.FormDocument" %>

<h1>${document.name}</h1>
<c:forEach var="field" items="document.formDocumentFields">
    <input type="checkbox" name="${document.id}_${field.id}" ${field.checked?"checked":""}>${field.field.name}</input>
    <br>
</c:forEach>
<br>
<button onclick="deleteDocument(${document.id})" style="float: right;">Удалить</button>