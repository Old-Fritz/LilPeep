<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="form" items="${forms}">
    <div class="docitem" onclick="window.location =  'editForm?formID=${form.id}'">
        <h1>${form.name}</h1>
        <br>
        <c:forEach var="document" items="${form.formDocuments}">
            ${document.documentKind.name}
            <br>
        </c:forEach>
            ${form.url}
    </div>
</c:forEach>