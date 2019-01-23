<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<c:forEach var="document" items="${documents}">
    <button onmouseenter="selectDocument('${document.id}', '${document.name}');">${document.name}</button>
    <br>
</c:forEach>