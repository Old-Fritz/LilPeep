<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="docitem" >
    <table>
        <c:forEach var="document" items="${documents}">
            <table  onclick="window.location = '${action}?documentID=${document.id}'">
                <tr>
                    <td>
                        <image src="${document.picture.url}" alt="Случился Бибиб"/>
                    </td>
                    <td>
                        <h1>${document.name}</h1>
                        <p>
                                ${document.description}
                        </p>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </table>
</div>