<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<<<<<<< HEAD
<h>${document.documentKind.name}</h>
<button onclick="deleteDocument(${document.id})">Удалить</button>
<br>
<c:forEach var="field" items="${document.formDocumentFields}">
=======
<h1>${document.name}</h1>
<c:forEach var="field" items="document.formDocumentFields">
>>>>>>> nekit
    <input type="checkbox" name="${document.id}_${field.id}" ${field.checked?"checked":""}>${field.field.name}</input>
    <br>
</c:forEach>
<br>
<button onclick="deleteDocument(${document.id})" style="float: right;">Удалить</button>