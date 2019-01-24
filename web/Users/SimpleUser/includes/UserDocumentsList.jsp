<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div >
    <table>
        <c:forEach var="document" items="${documents}">
            <table  class="docitem" onclick="window.location = '${action}?documentID=${document.id}'">
                <tr>
                    <td>
                        <image class="docimg" src="${document.documentKind.picture.url}" alt="Случился Бибиб"></image>
                    </td>
                    <td>
                        <h1>${document.documentKind.name}</h1>
                        <p>
                                ${document.documentKind.description}
                        </p>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </table>
</div>