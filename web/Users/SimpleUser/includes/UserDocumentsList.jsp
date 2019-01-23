<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <table class="docitem" onclick="window.location = '${action}?documentID=${document.id}'">
    <c:forEach var="document" items="${documents}">
        <tr>
            <td>
                <image src="${document.documentKind.picture.url}" alt="Случился Бибиб"/>
            </td>
            <td>
                <h1>${document.documentKind.name}</h1>
                <p>
                        ${document.documentKind.description}
                </p>
            </td>
            <td>
                <button >open</button>
            </td>
        </tr>
    </c:forEach>
    </table>
</div>