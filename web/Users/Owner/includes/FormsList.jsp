<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="form" items="${forms}">
    <h1>${form.name}</h1>
    <br>
    <c:forEach var="document" items="form.formDocuments">
        ${document.documentKind.name}
        <br>
    </c:forEach>
    ${form.url}
    <button style="float: right" {КОД НА ЖСП}>Изменить</button>
</c:forEach>