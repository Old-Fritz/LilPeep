<%@ c:taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:forEach var="document" items="documents">
    <button onclick="selectDocument(${document.id})">${document.name}</button>
    <br>
</c:forEach>
