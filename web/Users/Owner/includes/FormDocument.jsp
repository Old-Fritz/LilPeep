<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<h>${document.documentKind.name}</h>
<button onclick="deleteDocument(${document.id})">Удалить</button>
<br>
<c:forEach var="field" items="${document.formDocumentFields}">
    <input type="checkbox" name="${document.id}_${field.id}" ${field.checked?"checked":""}>${field.field.name}</input>
    <br>
</c:forEach>
<br>
<button onclick="deleteDocument(${document.id})" style="float: right;">Удалить</button>